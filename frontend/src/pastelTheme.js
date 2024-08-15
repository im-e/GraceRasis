import { createTheme } from '@mui/material/styles';

const pastelTheme = createTheme({
    palette: {
        primary: {
            main: '#FF9DC4', // Pastel pink, inspired by the characters' hair
            light: '#FFC4DD',
            dark: '#FF78AB',
            contrastText: '#FFFFFF',
        },
        secondary: {
            main: '#B088F9', // Pastel purple, inspired by the background
            light: '#D4BBFC',
            dark: '#8E5BF7',
            contrastText: '#FFFFFF',
        },
        background: {
            default: '#FFF0F5', // Very light pink background
            paper: '#FFFFFF',
        },
        text: {
            primary: '#4A4A4A',
            secondary: '#757575',
        },
        error: {
            main: '#FF6B6B', // Pastel red
        },
        warning: {
            main: '#FFD93D', // Pastel yellow
        },
        info: {
            main: '#4DD0E1', // Pastel blue, inspired by some accents in the image
        },
        success: {
            main: '#A5D6A7', // Pastel green
        },
    },
    typography: {
        fontFamily: "'Roboto', 'Helvetica', 'Arial', sans-serif",
        h1: {
            fontWeight: 700,
            color: '#FF78AB',
        },
        h2: {
            fontWeight: 600,
            color: '#8E5BF7',
        },
        button: {
            textTransform: 'none',
        },
    },
    components: {
        MuiButton: {
            styleOverrides: {
                root: {
                    borderRadius: 20,
                },
            },
        },
        MuiCard: {
            styleOverrides: {
                root: {
                    borderRadius: 16,
                    boxShadow: '0 4px 8px rgba(0,0,0,0.1)',
                },
            },
        },
    },
});

export default pastelTheme;