import React from 'react'
import ReactDOM from 'react-dom/client'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import App from './App.jsx'
import Home from './Home.jsx'
import { UserProvider } from './UserContext'; 
import './index.css'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
     <UserProvider> 
    <Router>
      <Routes>
        <Route path="/login" element={<App />} />
        <Route path="/home" element={<Home />} />
      </Routes>
    </Router>
    </UserProvider> 
  </React.StrictMode>,
)
