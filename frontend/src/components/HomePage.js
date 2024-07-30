import React from 'react';

function HomePage() {
    return (
        <div style={styles.container}>
            <h1>Welcome to Our Application</h1>
            <p>This is the homepage of our Spring-React application.</p>
            <button style={styles.button}>Learn More</button>
        </div>
    );
}

const styles = {
    container: {
        textAlign: 'center',
        padding: '2rem',
    },
    button: {
        backgroundColor: '#007bff',
        color: 'white',
        padding: '10px 20px',
        border: 'none',
        borderRadius: '5px',
        cursor: 'pointer',
    }
};

export default HomePage;