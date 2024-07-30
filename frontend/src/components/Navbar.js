import React from 'react';
import { Link } from 'react-router-dom';

function Navbar() {
    return (
        <nav style={styles.nav}>
            <Link to="/" style={styles.link}>Home</Link>
        </nav>
    );
}

const styles = {
    nav: {
        display: 'flex',
        justifyContent: 'space-around',
        padding: '1rem',
        backgroundColor: '#333',
    },
    link: {
        color: 'white',
        textDecoration: 'none',
    }
};

export default Navbar;