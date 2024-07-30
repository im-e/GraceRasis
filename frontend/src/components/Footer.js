import React from 'react';

function Footer() {
    return (
        <footer style={styles.footer}>
            <p>&copy; 2024 Your Company Name. All rights reserved.</p>
        </footer>
    );
}

const styles = {
    footer: {
        position: 'fixed',
        left: 0,
        bottom: 0,
        width: '100%',
        backgroundColor: '#333',
        color: 'white',
        textAlign: 'center',
        padding: '1rem',
    }
};

export default Footer;