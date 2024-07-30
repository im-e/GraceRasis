import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Navbar from './components/Navbar';
import Footer from './components/Footer';
import HomePage from "./components/HomePage";

function App() {
  return (
      <Router>
        <div>
          <Navbar />
          <Routes>
            <Route path="/" element={<HomePage/>} />
          </Routes>
          <Footer />
        </div>
      </Router>
  );
}

export default App;