import React, { useState, useEffect } from 'react';
import {
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Paper,
    Typography,
    Box
} from '@mui/material';

const VolforceTablePage = () => {
    const [volforceData, setVolforceData] = useState([]);

    useEffect(() => {
        // In a real application, you would fetch this data from your backend API
        // For this example, we'll generate some sample data
        const generateSampleData = () => {
            const levels = [16, 17, 18, 19, 20];
            const clearMedals = ['UC', 'EXC', 'C'];
            const scores = [1000, 999, 998, 997, 996, 995];

            const data = [];

            levels.forEach(level => {
                scores.forEach(score => {
                    clearMedals.forEach(medal => {
                        // This is a simplified calculation. In a real app, you'd call your backend API
                        const volforce = ((level * 2) * (score / 1000) * 0.001 * (medal === 'UC' ? 1.05 : medal === 'EXC' ? 1.02 : 1)).toFixed(3);
                        data.push({ level, score, medal, volforce });
                    });
                });
            });

            return data;
        };

        setVolforceData(generateSampleData());
    }, []);

    return (
        <Box sx={{ width: '100%', overflowX: 'auto' }}>
            <Typography variant="h4" gutterBottom>
                Volforce Chart
            </Typography>
            <TableContainer component={Paper}>
                <Table sx={{ minWidth: 650 }} aria-label="volforce table">
                    <TableHead>
                        <TableRow>
                            <TableCell>Level</TableCell>
                            <TableCell>Score</TableCell>
                            <TableCell>UC</TableCell>
                            <TableCell>EXC</TableCell>
                            <TableCell>C</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {volforceData.reduce((acc, row) => {
                            const existingRow = acc.find(r => r.level === row.level && r.score === row.score);
                            if (existingRow) {
                                existingRow[row.medal] = row.volforce;
                            } else {
                                acc.push({
                                    level: row.level,
                                    score: row.score,
                                    [row.medal]: row.volforce
                                });
                            }
                            return acc;
                        }, []).map((row, index) => (
                            <TableRow key={index}>
                                <TableCell>{row.level}</TableCell>
                                <TableCell>{row.score}</TableCell>
                                <TableCell>{row.UC}</TableCell>
                                <TableCell>{row.EXC}</TableCell>
                                <TableCell>{row.C}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Box>
    );
};

export default VolforceTablePage;