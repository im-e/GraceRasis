import React from 'react';
import { Box, Typography } from '@mui/material';

const LevelBox = ({ level, color, difficulty }) => {
    const boxColor = difficulty ? getFinalColor(difficulty) : color;

    return (
        <Box
            sx={{
                width: '24px',
                height: '24px',
                backgroundColor: boxColor,
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
                borderRadius: '4px',
            }}
        >
            <Typography
                variant="h7"
                sx={{
                    color: 'white',
                    fontWeight: 'bold',
                    textShadow: '0px 0px 4px rgba(0,0,0,1), 0px 0px 2px rgba(0,0,0,1)',
                }}
            >
                {level}
            </Typography>
        </Box>
    );
};

const getFinalColor = (difficulty) => {
    switch (difficulty) {
        case 'Infinite':
            return '#F955F0';
        case 'Gravity':
            return '#D27021';
        case 'Heavenly':
            return '#55D5F9';
        case 'Vivid':
            return '#FF61CA';
        case 'Exceed':
            return '#25E0FF';
        case 'Maximum':
            return '#e8e6e3';
        default:
            return '#f44336'; // Default Red
    }
};

export default LevelBox;