import React from 'react';
import { AppBar, Toolbar, Typography, Button } from '@mui/material';
import { Link as RouterLink } from 'react-router-dom';

function Header() {
    return (
        <AppBar position="static">
            <Toolbar>
                <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                    My Music Website
                </Typography>
                <Button color="inherit" component={RouterLink} to="/">Home</Button>
                <Button color="inherit" component={RouterLink} to="/music">Music</Button>
                <Button color="inherit" component={RouterLink} to="/about">About</Button>
                <Button color="inherit" component={RouterLink} to="/contact">Contact</Button>
            </Toolbar>
        </AppBar>
    );
}

export default Header;