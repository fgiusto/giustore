import { useMemo, useState } from 'react'
import './App.css'

const API_BASE =
  import.meta.env.VITE_SEARCH_API_BASE ??
  'http://localhost:8080/search/api/items/search'
const SEARCH_ENDPOINT = API_BASE.replace(/\/$/, '')

const suggestedQueries = [
  'organic honey',
  'sourdough bread',
  'mediterranean olives',
  'stone fruit',
  'oat milk',
]

const getYoutubeEmbedUrl = (url) => {
  if (!url) {
    return null
  }

  try {
    const parsed = new URL(url)
    const host = parsed.hostname.replace(/^www\./, '')

    if (host === 'youtu.be') {
      const id = parsed.pathname.replace('/', '').trim()
      return id ? `https://www.youtube.com/embed/${id}` : null
    }

    if (host === 'youtube.com' || host === 'm.youtube.com') {
      const id = parsed.searchParams.get('v')
      return id ? `https://www.youtube.com/embed/${id}` : null
    }
  } catch {
    return null
  }

  return null
}

function App() {
  const [query, setQuery] = useState('')
  const [results, setResults] = useState([])
  const [status, setStatus] = useState('idle')
  const [error, setError] = useState('')
  const [searchTimeMs, setSearchTimeMs] = useState(null)

  const trimmedQuery = query.trim()
  const hasSearched = status === 'success' || status === 'error'
  const hasResults = status === 'success' && results.length > 0

  const headline = useMemo(() => {
    if (hasSearched) {
      return 'Search the Giustore catalog'
    }
    return 'Find groceries like you find answers.'
  }, [hasSearched])

  const runSearch = async (value) => {
    if (!value) {
      return
    }

    const start = performance.now()
    setStatus('loading')
    setError('')

    try {
      const response = await fetch(
        `${SEARCH_ENDPOINT}?query=${encodeURIComponent(value)}`,
      )

      if (!response.ok) {
        throw new Error(`Search failed (${response.status})`)
      }

      const payload = await response.json()
      const elapsed = Math.round(performance.now() - start)

      setResults(Array.isArray(payload) ? payload : [])
      setSearchTimeMs(elapsed)
      setStatus('success')
    } catch (err) {
      setResults([])
      setSearchTimeMs(null)
      setStatus('error')
      setError(
        err instanceof Error
          ? err.message
          : 'Something went wrong with the search request.',
      )
    }
  }

  const handleSubmit = (event) => {
    event.preventDefault()
    runSearch(trimmedQuery)
  }

  const handleSuggestionClick = (value) => {
    setQuery(value)
    runSearch(value)
  }

  const handleClear = () => {
    setQuery('')
    setResults([])
    setStatus('idle')
    setError('')
    setSearchTimeMs(null)
  }

  return (
    <div className={`app ${hasSearched ? 'app--searched' : ''}`}>
      <header className="topbar">
        <div className="brand">
          <div className="brand-mark">g</div>
          <div>
            <div className="brand-name">giustore search</div>
            <div className="brand-tag">Local inventory, global speed</div>
          </div>
        </div>
        <div className="status-pill">Powered by the Giustore search API</div>
      </header>

      <main className="hero">
        <div className="hero-copy">
          <p className="eyebrow">Search smarter</p>
          <h1>{headline}</h1>
          <p className="lead">
            Type a query, press enter, and get fast, relevant results from your
            own backend search stack.
          </p>
        </div>

        <section className="search-shell">
          <form className="search-bar" onSubmit={handleSubmit}>
            <div className="search-input">
              <span className="search-icon" aria-hidden="true">
                search
              </span>
              <input
                type="search"
                placeholder="Search for groceries, brands, and staples"
                value={query}
                onChange={(event) => setQuery(event.target.value)}
                aria-label="Search Giustore"
              />
              {query && (
                <button
                  type="button"
                  className="icon-button"
                  onClick={handleClear}
                  aria-label="Clear search"
                >
                  x
                </button>
              )}
            </div>
            <div className="search-actions">
              <button className="primary" type="submit">
                Search
              </button>
              <button
                className="ghost"
                type="button"
                onClick={() =>
                  handleSuggestionClick(
                    suggestedQueries[
                      Math.floor(Math.random() * suggestedQueries.length)
                    ],
                  )
                }
              >
                I'm feeling local
              </button>
            </div>
          </form>

          <div className="suggestions">
            {suggestedQueries.map((item) => (
              <button
                key={item}
                type="button"
                className="chip"
                onClick={() => handleSuggestionClick(item)}
              >
                {item}
              </button>
            ))}
          </div>
        </section>

        <section className="results">
          {status === 'loading' && (
            <div className="results-state">
              <div className="loader" aria-hidden="true"></div>
              <div>Searching your catalog...</div>
            </div>
          )}

          {status === 'error' && (
            <div className="results-state error">
              <div className="state-title">We hit a snag.</div>
              <p>{error}</p>
              <p className="state-hint">
                Verify that the search service is running at{' '}
                <span className="mono">{SEARCH_ENDPOINT}</span>.
              </p>
            </div>
          )}

          {status === 'success' && (
            <>
              <div className="results-meta">
                <span>
                  {results.length} results
                  {searchTimeMs !== null ? ` in ${searchTimeMs} ms` : ''}
                </span>
                <span className="results-query">
                  Showing matches for "{trimmedQuery}"
                </span>
              </div>

              {hasResults ? (
                <div className="results-list">
                  {results.map((item, index) => {
                    const youtubeEmbedUrl = getYoutubeEmbedUrl(item.videoUrl)
                    return (
                      <article
                        key={item.id ?? `${item.title}-${index}`}
                        className="result-card"
                      >
                        <div className="result-body">
                          <div className="result-details">
                            <div className="result-header">
                              <div className="result-title">{item.title}</div>
                              {item.category?.name && (
                                <span className="pill">
                                  {item.category.name}
                                </span>
                              )}
                            </div>
                            <p className="result-description">
                              {item.description ||
                                'No description available yet.'}
                            </p>
                            <div className="result-footer">
                              <span>
                                Owner:{' '}
                                <strong>
                                  {item.owner?.name ?? 'Giustore inventory'}
                                </strong>
                              </span>
                              {item.id && (
                                <span className="mono">#{item.id}</span>
                              )}
                            </div>
                          </div>
                          {(youtubeEmbedUrl || item.videoUrl) && (
                            <div className="result-media">
                              {youtubeEmbedUrl ? (
                                <iframe
                                  src={youtubeEmbedUrl}
                                  title={`${item.title ?? 'Item'} video`}
                                  allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                                  allowFullScreen
                                />
                              ) : (
                                <video
                                  src={item.videoUrl}
                                  controls
                                  preload="metadata"
                                  playsInline
                                >
                                  Your browser does not support the video tag.
                                </video>
                              )}
                            </div>
                          )}
                        </div>
                      </article>
                    )
                  })}
                </div>
              ) : (
                <div className="results-state empty">
                  <div className="state-title">No matches found.</div>
                  <p>Try a different wording or a broader term.</p>
                </div>
              )}
            </>
          )}
        </section>
      </main>

      <footer className="footer">
        Giustore Search - Built for fast, local discovery.
      </footer>
    </div>
  )
}

export default App
