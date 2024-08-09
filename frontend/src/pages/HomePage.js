import React from 'react';
import { Container, Typography, Box } from '@mui/material';

function HomePage() {
    return (
        <Container maxWidth="sm">
            <Box sx={{ my: 4 }}>
                <Typography variant="h4" component="h1" gutterBottom>
                    Welcome to My Website
                </Typography>
                <Typography variant="body1">
                    This is a basic homepage created with React and Material-UI.
                    You can add more content and components here as needed.
                </Typography>
            </Box>
        </Container>
    );
}

export default HomePage;