import React from 'react';
import { CssBaseline, Box } from '@mui/material';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Header from './components/Header';
import Footer from './components/Footer';
import HomePage from './pages/HomePage';
import MusicPage from './pages/MusicPage';
import MusicDetailPage from './pages/MusicDetailPage';
import ChartListPage from './pages/ChartListPage';

function App() {
    return (
        <Router>
            <CssBaseline />
            <Box sx={{ display: 'flex', flexDirection: 'column', minHeight: '100vh' }}>
                <Header />
                <Box component="main" sx={{ flexGrow: 1, py: 3 }}>
                    <Routes>
                        <Route path="/" element={<HomePage />} />
                        <Route path="/music" element={<MusicPage />} />
                        <Route path="/music/:id" element={<MusicDetailPage />} />
                        <Route path="/charts" element={<ChartListPage />} />
                        <Route path="/charts/:difficulty" element={<ChartListPage />} />
                    </Routes>
                </Box>
                <Footer />
            </Box>
        </Router>
    );
}

export default App;