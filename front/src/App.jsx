import { useMemo, useState } from 'react'
import './App.css'

const API_BASE =
  import.meta.env.VITE_SEARCH_API_BASE ??
  'http://localhost:8080/search/api/items/search'
const SEARCH_ENDPOINT = API_BASE.replace(/\/$/, '')

const navItems = [
  'ACCUEIL',
  'NOS METIERS',
  'GROUPE GIUSTORE',
  'NEWS & PROMOS',
  'AGENCES',
  'NOS MARQUES',
  'ACCES PRO',
  'CONTACT',
  'NOS CATALOGUES',
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
  const [menuOpen, setMenuOpen] = useState(false)

  const trimmedQuery = query.trim()
  const hasSearched = status === 'success' || status === 'error'
  const hasResults = status === 'success' && results.length > 0

  const headline = useMemo(() => {
    if (hasSearched) {
      return 'Recherchez dans le catalogue Giustore'
    }
    return 'Trouvez vos produits en quelques secondes.'
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
        throw new Error(`La recherche a echoue (${response.status})`)
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
          : 'Une erreur est survenue lors de la requete de recherche.',
      )
    }
  }

  const handleSubmit = (event) => {
    event.preventDefault()
    runSearch(trimmedQuery)
  }

  return (
    <div className="app">
      <header className="pastor-header">
        <div className="brand-strip">
          <div className="brand-main">Giustore</div>
          <div className="brand-side">
            <div className="network-copy">MEMBRE DU RESEAU ALGOREL</div>
            <img
              src="/bleu_rouge.png"
              alt="Bleu Rouge"
              className="bleu-rouge-image"
            />
          </div>
        </div>

        <div className="claim-strip">Les professionnels sont la</div>

        <button
          type="button"
          className="mobile-nav-toggle"
          onClick={() => setMenuOpen((current) => !current)}
          aria-expanded={menuOpen}
          aria-controls="main-nav"
        >
          Menu
        </button>

        <nav id="main-nav" className={`main-nav ${menuOpen ? 'is-open' : ''}`}>
          <ul>
            {navItems.map((item) => (
              <li
                key={item}
                className={item === 'NOS CATALOGUES' ? 'is-highlight' : ''}
              >
                <a
                  href="#"
                  onClick={() => {
                    setMenuOpen(false)
                  }}
                >
                  {item}
                </a>
              </li>
            ))}
          </ul>
        </nav>
      </header>

      <main className="content">
        <section className="hero-copy">
          <h1>{headline}</h1>
          <p>
            Saisissez une requete puis obtenez des resultats rapides depuis
            votre service de recherche.
          </p>
        </section>

        <section className="search-shell">
          <form className="search-bar" onSubmit={handleSubmit}>
            <input
              type="search"
              placeholder="Rechercher un produit, une marque, une categorie"
              value={query}
              onChange={(event) => setQuery(event.target.value)}
              aria-label="Recherche Giustore"
            />
            <div className="search-actions">
              <button className="primary" type="submit">
                Rechercher
              </button>
            </div>
          </form>
        </section>

        <section className="results">
          {status === 'loading' && (
            <div className="results-state">Recherche en cours...</div>
          )}

          {status === 'error' && (
            <div className="results-state error">
              <div className="state-title">Une erreur est survenue.</div>
              <p>{error}</p>
              <p className="state-hint">
                Verifiez que le service est disponible sur{' '}
                <span className="mono">{SEARCH_ENDPOINT}</span>.
              </p>
            </div>
          )}

          {status === 'success' && (
            <>
              <div className="results-meta">
                <span>
                  {results.length} resultats
                  {searchTimeMs !== null ? ` en ${searchTimeMs} ms` : ''}
                </span>
                <span className="results-query">
                  Resultats pour "{trimmedQuery}"
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
                                <span className="pill">{item.category.name}</span>
                              )}
                            </div>
                            <p className="result-description">
                              {item.description ||
                                'Aucune description disponible pour le moment.'}
                            </p>

                          </div>
                          {(youtubeEmbedUrl || item.videoUrl) && (
                            <div className="result-media">
                              {youtubeEmbedUrl ? (
                                <iframe
                                  src={youtubeEmbedUrl}
                                  title={`${item.title ?? 'Article'} video`}
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
                                  Votre navigateur ne prend pas en charge la
                                  balise video.
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
                  <div className="state-title">Aucun resultat trouve.</div>
                  <p>Essayez un terme plus large ou une autre formulation.</p>
                </div>
              )}
            </>
          )}
        </section>
      </main>
    </div>
  )
}

export default App

