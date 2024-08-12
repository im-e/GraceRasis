import React from 'react';
import {
    AppBar,
    Toolbar,
    Typography,
    Container,
    Grid,
    Paper,
    TextField,
    InputAdornment,
    Box,
    List,
    ListItem,
    ListItemText,
    ThemeProvider,
    createTheme,
    CssBaseline
} from '@mui/material';
import { Search, MusicNote, BarChart, FlashOn, Album } from '@mui/icons-material';

const theme = createTheme({
    palette: {
        primary: {
            main: '#3f51b5',
        },
        secondary: {
            main: '#f50057',
        },
        background: {
            default: '#f5f5f5',
        },
    },
});

const StatCard = ({ icon: Icon, title, value }) => (
    <Paper elevation={3} sx={{ p: 2, height: '100%', display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center' }}>
        <Icon color="primary" sx={{ fontSize: 40, mb: 1 }} />
        <Typography variant="h6" component="h3" gutterBottom>
            {title}
        </Typography>
        <Typography variant="h4" component="p">
            {value}
        </Typography>
    </Paper>
);

export default function HomePage() {
    return (
        <ThemeProvider theme={theme}>
            <CssBaseline />
            <Box sx={{ flexGrow: 1, minHeight: '100vh', display: 'flex', flexDirection: 'column' }}>
                <Container maxWidth="lg" sx={{ mt: 4, mb: 4, flexGrow: 1 }}>
                    <Box sx={{ mb: 6, textAlign: 'center' }}>
                        <Typography variant="h2" component="h1" gutterBottom>
                            GraceRasis
                        </Typography>
                        <Typography variant="h5" component="h2" gutterBottom color="textSecondary">
                            Your Ultimate Sound Voltex Information Hub
                        </Typography>
                        <Typography variant="body1" paragraph sx={{ maxWidth: '800px', margin: '0 auto' }}>
                            GraceRasis is a comprehensive tool designed for Sound Voltex enthusiasts.
                            Our platform offers detailed track and chart information, VOLFORCE calculations,
                            and radar value analysis. Whether you're a beginner looking to improve or a
                            seasoned player aiming for the top, GraceRasis provides the insights you need
                            to elevate your Sound Voltex experience.
                        </Typography>
                    </Box>

                    <Grid container spacing={3}>
                        <Grid item xs={12} sm={6} md={3}>
                            <StatCard icon={MusicNote} title="Track Searcher"/>
                        </Grid>
                        <Grid item xs={12} sm={6} md={3}>
                            <StatCard icon={BarChart} title="Chart Searcher"/>
                        </Grid>
                        <Grid item xs={12} sm={6} md={3}>
                            <StatCard icon={FlashOn} title="VOLFORCE Calculator" />
                        </Grid>
                        <Grid item xs={12} sm={6} md={3}>
                            <StatCard icon={Album} title="Radar Values"/>
                        </Grid>

                        <Grid item xs={12} md={6}>
                            <Paper elevation={3} sx={{ p: 2, height: '100%' }}>
                                <Typography variant="h6" component="h2" gutterBottom>
                                    Latest Updates
                                </Typography>
                                <List>
                                    <ListItem>
                                        <ListItemText primary="Added new songs from EXCEED GEAR update" />
                                    </ListItem>
                                    <ListItem>
                                        <ListItemText primary="Updated VOLFORCE calculations for recent charts" />
                                    </ListItem>
                                    <ListItem>
                                        <ListItemText primary="Improved search functionality" />
                                    </ListItem>
                                    <ListItem>
                                        <ListItemText primary="Added detailed information for HEAVENLY charts" />
                                    </ListItem>
                                </List>
                            </Paper>
                        </Grid>

                        <Grid item xs={12} md={6}>
                            <Paper elevation={3} sx={{ p: 2, height: '100%' }}>
                                <Typography variant="h6" component="h2" gutterBottom>
                                    Quick Tips
                                </Typography>
                                <List>
                                    <ListItem>
                                        <ListItemText primary="Use the search bar to quickly find songs or artists" />
                                    </ListItem>
                                    <ListItem>
                                        <ListItemText primary="Click on a song to see detailed chart information" />
                                    </ListItem>
                                    <ListItem>
                                        <ListItemText primary="Check your VOLFORCE progress in the player stats section" />
                                    </ListItem>
                                    <ListItem>
                                        <ListItemText primary="Filter charts by difficulty level or chart type" />
                                    </ListItem>
                                </List>
                            </Paper>
                        </Grid>
                    </Grid>
                </Container>

                <Box component="footer" sx={{ bgcolor: 'background.paper', py: 2, mt: 'auto' }}>
                    <Container maxWidth="lg">
                        <Typography variant="body2" color="text.secondary" align="center">
                            © 2024 GraceRasis - Sound Voltex Information Tool
                        </Typography>
                    </Container>
                </Box>
            </Box>
        </ThemeProvider>
    );
}