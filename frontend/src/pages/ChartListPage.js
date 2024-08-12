import React, { useState, useEffect } from 'react';
import {
    Container,
    Typography,
    Grid,
    Card,
    CardContent,
    Button,
    TextField,
    CircularProgress,
    Pagination
} from '@mui/material';
import { useNavigate, useParams } from 'react-router-dom';

function ChartListPage() {
    const [charts, setCharts] = useState([]);
    const [filteredCharts, setFilteredCharts] = useState([]);
    const [loading, setLoading] = useState(false);
    const [page, setPage] = useState(1);
    const [searchTerm, setSearchTerm] = useState('');
    const chartsPerPage = 50;
    const navigate = useNavigate();
    const { difficulty } = useParams();

    useEffect(() => {
        if (difficulty) {
            fetchCharts(difficulty);
        }
    }, [difficulty]);

    useEffect(() => {
        const filtered = charts.filter(chart =>
            chart.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
            chart.artist.toLowerCase().includes(searchTerm.toLowerCase())
        );
        setFilteredCharts(filtered);
        setPage(1);
    }, [searchTerm, charts]);

    const fetchCharts = async (level) => {
        setLoading(true);
        try {
            const response = await fetch(`/chart/level/${level}`);
            const data = await response.json();
            setCharts(data);
            setFilteredCharts(data);
        } catch (error) {
            console.error('Error fetching charts:', error);
        }
        setLoading(false);
    };

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const handlePageChange = (event, value) => {
        setPage(value);
    };

    const paginatedCharts = filteredCharts.slice((page - 1) * chartsPerPage, page * chartsPerPage);
    const totalPages = Math.ceil(filteredCharts.length / chartsPerPage);

    if (!difficulty) {
        return (
            <Container maxWidth="md" sx={{ py: 4 }}>
                <Typography variant="h4" component="h1" gutterBottom>
                    Select Difficulty Level
                </Typography>
                <Grid container spacing={2}>
                    {[...Array(20)].map((_, index) => (
                        <Grid item xs={6} sm={4} md={3} key={index}>
                            <Button
                                variant="contained"
                                fullWidth
                                onClick={() => navigate(`/charts/${index + 1}`)}
                            >
                                Level {index + 1}
                            </Button>
                        </Grid>
                    ))}
                </Grid>
            </Container>
        );
    }

    return (
        <Container maxWidth="lg" sx={{ py: 4 }}>
            <Typography variant="h4" component="h1" gutterBottom>
                Charts - Level {difficulty}
            </Typography>
            <TextField
                fullWidth
                variant="outlined"
                placeholder="Search charts..."
                value={searchTerm}
                onChange={handleSearch}
                sx={{ mb: 2 }}
            />
            {loading ? (
                <CircularProgress />
            ) : (
                <>
                    <Grid container spacing={2}>
                        {paginatedCharts.map((chart) => (
                            <Grid item xs={12} sm={6} md={4} key={chart.id}>
                                <Card>
                                    <CardContent>
                                        <Typography variant="h6" component="div">
                                            {chart.title}
                                        </Typography>
                                        <Typography variant="body2" color="text.secondary">
                                            Artist: {chart.artist}
                                        </Typography>
                                        <Typography variant="body2" color="text.secondary">
                                            Difficulty: {chart.difficulty}
                                        </Typography>
                                    </CardContent>
                                </Card>
                            </Grid>
                        ))}
                    </Grid>
                    <Pagination
                        count={totalPages}
                        page={page}
                        onChange={handlePageChange}
                        sx={{ mt: 2, display: 'flex', justifyContent: 'center' }}
                    />
                </>
            )}
        </Container>
    );
}

export default ChartListPage;