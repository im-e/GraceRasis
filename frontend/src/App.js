import React from 'react';
import { CssBaseline, Box } from '@mui/material';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { ThemeProvider } from '@mui/material/styles';
import pastelTheme from './pastelTheme';
import Header from './components/Header';
import Footer from './components/Footer';
import HomePage from './pages/HomePage';
import MusicPage from './pages/MusicPage';
import MusicDetailPage from './pages/MusicDetailPage';
import ChartListPage from './pages/ChartListPage';
import VolforceTablePage from "./pages/VolforceTablePage";



function App() {
    return (
        <ThemeProvider theme={pastelTheme}>
            <Router>
                <CssBaseline />
                <Box sx={{ display: 'flex', flexDirection: 'column', minHeight: '100vh' }}>
                    <Header />
                    <Box component="main" sx={{ flexGrow: 1, py: 3 }}>
                        <Routes>
                            <Route path="/" element={<HomePage/>} />
                            <Route path="/music" element={<MusicPage/>} />
                            <Route path="/music/:id" element={<MusicDetailPage/>} />
                            <Route path="/charts" element={<ChartListPage/>} />
                            <Route path="/charts/:difficulty" element={<ChartListPage/>} />
                            <Route path="/volforce" element={<VolforceTablePage/>} />
                        </Routes>
                    </Box>
                    <Footer />
                </Box>
            </Router>
        </ThemeProvider>
    );
}

export default App;