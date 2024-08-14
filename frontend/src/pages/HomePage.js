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
    CssBaseline,
    Card,
    CardContent,
    CardMedia
} from '@mui/material';
import { Search, MusicNote, BarChart, FlashOn, Album, Image } from '@mui/icons-material';
import pastelTheme from '../pastelTheme';
import headingImage from '../images/sdvx_banner.webp';

const StatCard = ({ icon: Icon, title, value }) => (
    <Card elevation={3} sx={{ height: '100%', display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', bgcolor: 'background.paper', transition: '0.3s', '&:hover': { transform: 'translateY(-5px)', boxShadow: 6 } }}>
        <CardContent sx={{ textAlign: 'center' }}>
            <Icon color="primary" sx={{ fontSize: 48, mb: 2 }} />
            <Typography variant="h6" component="h3" gutterBottom>
                {title}
            </Typography>
            <Typography variant="h5" component="p" color="text.secondary">
                {value}
            </Typography>
        </CardContent>
    </Card>
);

const InfoCard = ({ title, children }) => (
    <Card elevation={3} sx={{ height: '100%', bgcolor: 'background.paper', transition: '0.3s', '&:hover': { transform: 'translateY(-5px)', boxShadow: 6 } }}>
        <CardContent>
            <Typography variant="h6" component="h2" gutterBottom color="primary">
                {title}
            </Typography>
            {children}
        </CardContent>
    </Card>
);

export default function HomePage() {
    return (
        <ThemeProvider theme={pastelTheme}>
            <CssBaseline />
            <Box sx={{ flexGrow: 1, minHeight: '100vh', display: 'flex', flexDirection: 'column' }}>
                <Box sx={{ width: '100%', height: '300px', overflow: 'hidden', position: 'relative' }}>
                    <img
                        src={headingImage}
                        alt="Sound Voltex Banner"
                        style={{
                            width: '100%',
                            height: '100%',
                            objectFit: 'cover',
                            objectPosition: 'center'
                        }}
                    />
                    <Box sx={{
                        position: 'absolute',
                        top: 0,
                        left: 0,
                        width: '100%',
                        height: '100%',
                        display: 'flex',
                        flexDirection: 'column',
                        justifyContent: 'center',
                        alignItems: 'center',
                        backgroundColor: 'rgba(0, 0, 0, 0.5)'
                    }}>
                        <Typography variant="h2" component="h1" gutterBottom sx={{ color: 'white', textShadow: '2px 2px 4px rgba(0,0,0,0.5)' }}>
                            GraceRasis
                        </Typography>
                        <Typography variant="h5" component="h2" gutterBottom sx={{ color: 'white', textShadow: '1px 1px 2px rgba(0,0,0,0.5)' }}>
                            Your Ultimate Sound Voltex Information Hub
                        </Typography>
                    </Box>
                </Box>

                <Container maxWidth="lg" sx={{ mt: 4, mb: 4, flexGrow: 1 }}>
                    <Box sx={{ mb: 6, textAlign: 'center' }}>
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
                            <StatCard icon={MusicNote} title="Track Searcher" value="Explore" />
                        </Grid>
                        <Grid item xs={12} sm={6} md={3}>
                            <StatCard icon={BarChart} title="Chart Searcher" value="Analyze" />
                        </Grid>
                        <Grid item xs={12} sm={6} md={3}>
                            <StatCard icon={FlashOn} title="VOLFORCE Calculator" value="Calculate" />
                        </Grid>
                        <Grid item xs={12} sm={6} md={3}>
                            <StatCard icon={Album} title="Radar Values" value="Visualize" />
                        </Grid>

                        <Grid item xs={12} md={6}>
                            <InfoCard title="Latest Updates">
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
                            </InfoCard>
                        </Grid>

                        <Grid item xs={12} md={6}>
                            <InfoCard title="Quick Tips">
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
                            </InfoCard>
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