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
    Box,
    TextField,
    Button,
    CircularProgress,
    Grid,
    Container,
    MenuItem
} from '@mui/material';

const VolforceTablePage = () => {
    const [volforceData, setVolforceData] = useState({});
    const [customQuery, setCustomQuery] = useState({ level: '', score: '', clearMedal: 'C' });
    const [customResult, setCustomResult] = useState(null);
    const [loading, setLoading] = useState(true);

    const levels = [20, 19, 18, 17, 16, 15];
    const clearMedals = ['UC', 'EXC', 'C'];
    const scoreBoundaries = [1000, 998, 996, 994, 992, 990, 985, 980, 970, 960, 950];

    const rankThresholds = [
        { rank: '14.0 - Cyan I', threshold: '0.28'      , color: '#25B7C0FF'},
        { rank: '14.5 - Cyan III', threshold: '0.29'    , color: '#25B7C0FF'    },
        { rank: '15.0 - Scarlet I', threshold: '0.30'   , color: '#F97489FF'    },
        { rank: '15.5 - Scarlet III', threshold: '0.31' , color: '#F97489FF'        },
        { rank: '16.0 - Coral I', threshold: '0.32'     , color: '#FF69B4FF'    },
        { rank: '16.5 - Coral III', threshold: '0.33'   , color: '#FF69B4FF'    },
        { rank: '17.0 - Argento I', threshold: '0.34'   , color: '#D5DDEFFF'    },
        { rank: '17.5 - Argento III', threshold: '0.35' , color: '#D5DDEFFF'        },
        { rank: '18.0 - Eldora I', threshold: '0.36'    , color: '#FFD700FF'    },
        { rank: '18.5 - Eldora III', threshold: '0.37'  , color: '#FFD700FF'    },
        { rank: '19.0 - Crimson I', threshold: '0.38'   , color: '#FF0000FF'    },
        { rank: '19.5 - Crimson III', threshold: '0.39' , color: '#FF0000FF'        },
        { rank: '20.0 - Imperial I', threshold: '0.40'  , color: '#800080FF'    },
        { rank: '21.0 - Imperial II', threshold: '0.42' , color: '#800080FF'        },
        { rank: '22.0 - Imperial III', threshold: '0.44', color: '#800080FF'        },
        { rank: '23.0 - Imperial IV', threshold: '0.46' , color: '#800080FF'        }
    ];

    const getColorForVolforce = (volforce) => {
        const value = parseFloat(volforce);
        if (value <= 0.289) return '#bef9ff';
        if (value >= 0.290 && value <= 0.299) return '#25b7c0';
        if (value >= 0.300 && value <= 0.309) return '#ffb1bd';
        if (value >= 0.310 && value <= 0.319) return '#f97489';
        if (value >= 0.320 && value <= 0.329) return '#ff97ca';
        if (value >= 0.330 && value <= 0.339) return '#ff69b4';
        if (value >= 0.340 && value <= 0.349) return '#abb4c4';
        if (value >= 0.350 && value <= 0.359) return '#d5ddef';
        if (value >= 0.360 && value <= 0.369) return '#ffe180';
        if (value >= 0.370 && value <= 0.379) return '#ffd700';
        if (value >= 0.380 && value <= 0.389) return '#ff6d7a';
        if (value >= 0.390 && value <= 0.399) return '#ff0000';
        if (value >= 0.400 && value <= 0.409) return '#dda3ff';
        if (value >= 0.410 && value <= 0.419) return '#cf69ff';
        if (value >= 0.420 && value <= 0.429) return '#bf2dff';
        if (value >= 0.430 && value <= 0.435) return '#b500ff';
        if (value >= 0.436 && value <= 0.437) return '#e334e3';
        if (value >= 0.438 && value <= 0.439) return '#c71bc7';
        if (value >= 0.440 && value <= 0.459) return '#ad00ad';
        if (value >= 0.46) return '#800080';
        return 'white'; // Default color
    };

    useEffect(() => {
        fetchVolforceData();
    }, []);

    const fetchVolforceData = async () => {
        setLoading(true);
        try {
            const data = {};
            for (const level of levels) {
                const levelData = [];
                for (const score of scoreBoundaries) {
                    const row = { score };
                    for (const medal of clearMedals) {
                        const response = await fetch(`/volforce?level=${level}&score=${score}&clearMedal=${medal}`);
                        const result = await response.json();
                        row[medal] = result.volforce;
                    }
                    levelData.push(row);
                }
                data[level] = levelData;
            }
            setVolforceData(data);
        } catch (error) {
            console.error('Error fetching volforce data:', error);
        }
        setLoading(false);
    };

    const handleCustomQuery = async () => {
        if (!customQuery.level || !customQuery.score) return;

        setLoading(true);
        try {
            const response = await fetch(`/volforce?level=${customQuery.level}&score=${customQuery.score}&clearMedal=${customQuery.clearMedal}`);
            const result = await response.json();
            setCustomResult(result);
        } catch (error) {
            console.error('Error fetching custom volforce data:', error);
        }
        setLoading(false);
    };



    if (loading) {
        return (
            <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
                <CircularProgress />
            </Box>
        );
    }

    return (
        <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
            <Typography variant="h4" gutterBottom align="center">
                Volforce Tables
            </Typography>

            <Paper sx={{ p: 3, mb: 4, backgroundColor: 'primary.light'}}>
                <Typography variant="h6" gutterBottom>
                    Custom Query
                </Typography>
                <Grid container spacing={2} alignItems="center">
                    <Grid item xs={12} sm={3}>
                        <TextField
                            fullWidth
                            label="Level"
                            type="number"
                            value={customQuery.level}
                            onChange={(e) => setCustomQuery({ ...customQuery, level: e.target.value })}
                            InputProps={{ inputProps: { min: 1 } }}
                        />
                    </Grid>
                    <Grid item xs={12} sm={3}>
                        <TextField
                            fullWidth
                            label="Score"
                            type="number"
                            value={customQuery.score}
                            onChange={(e) => setCustomQuery({ ...customQuery, score: e.target.value })}
                            InputProps={{ inputProps: { min: 0, max: 10000000 } }}
                        />
                    </Grid>
                    <Grid item xs={12} sm={3}>
                        <TextField
                            fullWidth
                            select
                            label="Clear Medal"
                            value={customQuery.clearMedal}
                            onChange={(e) => setCustomQuery({ ...customQuery, clearMedal: e.target.value })}
                        >
                            {clearMedals.map((medal) => (
                                <MenuItem key={medal} value={medal}>
                                    {medal}
                                </MenuItem>
                            ))}
                        </TextField>
                    </Grid>
                    <Grid item xs={12} sm={3}>
                        <Button
                            fullWidth
                            onClick={handleCustomQuery}
                            variant="contained"
                            disabled={!customQuery.level || !customQuery.score}
                        >
                            Calculate
                        </Button>
                    </Grid>
                </Grid>
            </Paper>

            {customResult && (
                <TableContainer component={Paper} sx={{ mb: 4 }}>
                    <Table aria-label="custom query result">
                        <TableHead>
                            <TableRow>
                                <TableCell>Level</TableCell>
                                <TableCell>Score</TableCell>
                                <TableCell>Clear Medal</TableCell>
                                <TableCell>Volforce</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            <TableRow>
                                <TableCell>{customResult.level}</TableCell>
                                <TableCell>{customResult.score}</TableCell>
                                <TableCell>{customResult.clearMedal}</TableCell>
                                <TableCell sx={{
                                    backgroundColor: getColorForVolforce(customResult.volforce),
                                    color: 'black'}}
                                >
                                    {customResult.volforce}
                                </TableCell>
                            </TableRow>
                        </TableBody>
                    </Table>
                </TableContainer>
            )}

            <Paper sx={{ p: 2, mb: 4, backgroundColor: 'primary.light'}}>
                <Typography variant="h6" gutterBottom align="center">
                    Volforce Rank Thresholds
                </Typography>
                <TableContainer>
                    <Table size="small" aria-label="volforce rank thresholds">
                        <TableHead>
                            <TableRow>
                                <TableCell>Rank</TableCell>
                                <TableCell>Minimum Volforce</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {rankThresholds.map((row) => (
                                <TableRow key={row.rank}>
                                    <TableCell
                                        sx={{
                                            backgroundColor: row.color,
                                            color: 'black'
                                        }}
                                    >
                                        {row.rank}
                                    </TableCell>
                                    <TableCell sx={{
                                        backgroundColor: getColorForVolforce(row.threshold),
                                        color: 'black'}}>
                                        {row.threshold}
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Paper>

            <Grid container spacing={4}>
                {levels.map((level, index) => (
                    <Grid item xs={12} md={6} key={level}>
                        <Paper sx={{ p: 2, backgroundColor: 'primary.light'}}>
                            <Typography variant="h5" gutterBottom align="center">
                                Level {level}
                            </Typography>
                            <TableContainer>
                                <Table size="small" aria-label={`volforce table for level ${level}`}>
                                    <TableHead>
                                        <TableRow>
                                            <TableCell>Score</TableCell>
                                            {clearMedals.map(medal => (
                                                <TableCell key={medal}>{medal}</TableCell>
                                            ))}
                                        </TableRow>
                                    </TableHead>
                                    <TableBody>
                                        {volforceData[level]?.map((row, index) => (
                                            <TableRow key={index}>
                                                <TableCell>{row.score}</TableCell>
                                                {clearMedals.map(medal => (
                                                    <TableCell
                                                        key={medal}
                                                        sx={{
                                                            backgroundColor: getColorForVolforce(row[medal]),
                                                            color: 'black'
                                                        }}
                                                    >
                                                        {row[medal]}
                                                    </TableCell>
                                                ))}
                                            </TableRow>
                                        ))}
                                    </TableBody>
                                </Table>
                            </TableContainer>
                        </Paper>
                    </Grid>
                ))}
            </Grid>
        </Container>
    );
};

export default VolforceTablePage;