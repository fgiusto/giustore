import './App.css'

function App() {
  return (
    <div className="app">
      <div className="glow" aria-hidden="true"></div>
      <header className="topbar">
        <div className="brand">
          <div className="brand-mark">G</div>
          <div>
            <div className="brand-name">giustore</div>
            <div className="brand-tag">Grocery that feels local and fast</div>
          </div>
        </div>
        <nav className="nav">
          <a href="#" className="nav-link">Shop</a>
          <a href="#" className="nav-link">Deals</a>
          <a href="#" className="nav-link">Partners</a>
          <button className="nav-cta">Get the app</button>
        </nav>
      </header>

      <main className="hero">
        <section className="hero-copy">
          <p className="eyebrow">Fresh pantry in 30 minutes</p>
          <h1>Stock the kitchen with seasonal goods and everyday essentials.</h1>
          <p className="lead">
            Giustore connects you to neighborhood suppliers, with smart substitutions,
            clear pricing, and delivery windows you can count on.
          </p>
          <div className="cta-row">
            <button className="primary">Start a basket</button>
            <button className="ghost">Browse weekly specials</button>
          </div>
          <div className="trust">
            <div className="trust-item">
              <span className="trust-value">4.9</span>
              <span className="trust-label">average rating</span>
            </div>
            <div className="trust-item">
              <span className="trust-value">120+</span>
              <span className="trust-label">local producers</span>
            </div>
            <div className="trust-item">
              <span className="trust-value">30 min</span>
              <span className="trust-label">delivery promise</span>
            </div>
          </div>
        </section>

        <section className="hero-card">
          <div className="card-header">
            <span>Tonight's basket</span>
            <span className="pill">Delivered 6:20 pm</span>
          </div>
          <ul className="item-list">
            <li>
              <span>Heirloom tomatoes</span>
              <span>$6.40</span>
            </li>
            <li>
              <span>Herb focaccia</span>
              <span>$5.80</span>
            </li>
            <li>
              <span>Baby greens</span>
              <span>$4.10</span>
            </li>
            <li>
              <span>Citrus sparkling water</span>
              <span>$3.50</span>
            </li>
          </ul>
          <div className="card-footer">
            <div>
              <div className="price-label">Estimated total</div>
              <div className="price">$42.80</div>
            </div>
            <button className="primary small">Check out</button>
          </div>
        </section>
      </main>

      <section className="features">
        <div className="feature-card">
          <h3>Curated freshness</h3>
          <p>Handpicked produce with daily quality checks and seasonal spotlights.</p>
        </div>
        <div className="feature-card">
          <h3>Smart substitutions</h3>
          <p>Approve swaps ahead of time so your order stays on budget.</p>
        </div>
        <div className="feature-card">
          <h3>Neighborhood partners</h3>
          <p>Support bakeries, butchers, and growers within a few miles.</p>
        </div>
      </section>

      <footer className="footer">
        Built for Giustore customers who value quality, speed, and local craft.
      </footer>
    </div>
  )
}

export default App
