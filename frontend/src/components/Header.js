import React from 'react';
import { AppBar, Toolbar, Button } from '@mui/material';
import { Link as RouterLink } from 'react-router-dom';

function Header() {
    return (
        <AppBar position="static">
            <Toolbar>
                <Button variant="text" size="large" color="inherit" sx={{ flexGrow: 1 }} component={RouterLink} to="/">
                    GraceRasis
                </Button>
                <Button color="inherit" component={RouterLink} to="/music">Music</Button>
                <Button color="inherit" component={RouterLink} to="/charts">Charts</Button>
            </Toolbar>
        </AppBar>
    );
}

export default Header;