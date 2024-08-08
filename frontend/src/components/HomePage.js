import React from 'react';

function HomePage() {
    return (
        <div style={styles.container}>
            <h1>GraceRasis</h1>
            <p>GraceRasis: Your one-stop resource for all things Sound Voltex. Easily search our extensive database of music, charts, and volforce tables. Get detailed information on songs, difficulty ratings, and optimal scoring strategies. Stay updated with the latest game updates, events, and community news. Whether you're a beginner or a seasoned player, GraceRasis has the tools and information you need to elevate your game.</p>
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