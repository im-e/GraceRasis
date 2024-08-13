import React, { useState, useEffect, useMemo, useCallback } from 'react';
import {
    Container,
    Typography,
    Grid,
    Card,
    CardMedia,
    CardContent,
    Box,
    TextField,
    CircularProgress,
    Pagination,
    Select,
    MenuItem,
    FormControl,
    InputLabel,
    Alert,
    IconButton,
    Button,
} from '@mui/material';
import { ArrowUpward, ArrowDownward } from '@mui/icons-material';
import { useNavigate, useParams } from 'react-router-dom';
import { debounce } from 'lodash';
import {
    Chart as ChartJS,
    RadialLinearScale,
    PointElement,
    LineElement,
    Filler,
    Tooltip,
    Legend
} from 'chart.js';
import { Radar } from 'react-chartjs-2';

ChartJS.register(
    RadialLinearScale,
    PointElement,
    LineElement,
    Filler,
    Tooltip,
    Legend
);

const ITEMS_PER_PAGE = 24;

function CenteredRadarChart({ chartData }) {
    const chartRef = React.useRef(null);

    React.useEffect(() => {
        const chart = chartRef.current;

        if (chart) {
            const updateGradient = () => {
                const ctx = chart.ctx;
                const chartArea = chart.chartArea;
                const centerX = (chartArea.left + chartArea.right) / 2;
                const centerY = (chartArea.top + chartArea.bottom) / 2;
                const radius = Math.min(chartArea.right - chartArea.left, chartArea.bottom - chartArea.top) / 2;

                const gradient = ctx.createRadialGradient(centerX, centerY, 0, centerX, centerY, radius);
                gradient.addColorStop(0, 'rgba(105,136,250,0.93)');
                gradient.addColorStop(1, 'rgba(208,35,67,0.84)');

                chart.data.datasets[0].backgroundColor = gradient;
                chart.update();
            };

            chart.options.responsive = true;
            chart.options.onResize = updateGradient;
            updateGradient();
        }
    }, [chartData]);

    const options = {
        scales: {
            r: {
                min: 0,
                max: 100,
                ticks: { stepSize: 20, display: false },
                pointLabels: {
                    font: { size: 12, weight: 'bold' },
                    color: (context) => {
                        const colors = ['#00FFFF', '#FF0000', '#FF00FF', '#FFFF00', '#8A2BE2', '#00FF00'];
                        return colors[context.index];
                    },
                },
                grid: { color: 'rgb(0,0,0)' },
            }
        },
        plugins: {
            legend: { display: false },
            tooltip: { enabled: false },
        },
        elements: {
            point: { radius: 0 },
            line: { borderWidth: 2 },
        },
        maintainAspectRatio: false,
    };

    return (
        <Radar ref={chartRef} data={chartData} options={options} />
    );
}

function ChartListPage() {
    const [allCharts, setAllCharts] = useState([]);
    const [displayedCharts, setDisplayedCharts] = useState([]);
    const [titleSearch, setTitleSearch] = useState('');
    const [artistSearch, setArtistSearch] = useState('');
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [page, setPage] = useState(1);
    const [sortBy, setSortBy] = useState('title');
    const [sortOrder, setSortOrder] = useState('asc');
    const navigate = useNavigate();
    const { difficulty } = useParams();

    const fetchCharts = useCallback(async () => {
        if (!difficulty) return;
        setLoading(true);
        setError(null);
        try {
            let url = `/chart/level/${difficulty}`;
            const params = new URLSearchParams();
            if (titleSearch) params.append('title', titleSearch);
            if (artistSearch) params.append('artist', artistSearch);
            if (params.toString()) {
                url += `?${params.toString()}`;
            }

            const response = await fetch(url);
            if (!response.ok) {
                throw new Error('Failed to fetch chart data');
            }
            const data = await response.json();
            setAllCharts(Array.isArray(data) ? data : []);
        } catch (error) {
            console.error('Error fetching charts:', error);
            setError('Failed to load chart data. Please try again later.');
            setAllCharts([]);
        }
        setLoading(false);
    }, [difficulty, titleSearch, artistSearch]);

    const debouncedFetchCharts = useMemo(
        () => debounce(fetchCharts, 300),
        [fetchCharts]
    );

    useEffect(() => {
        debouncedFetchCharts();
        return () => debouncedFetchCharts.cancel();
    }, [debouncedFetchCharts]);

    const sortedCharts = useMemo(() => {
        return [...allCharts].sort((a, b) => {
            let comparison = 0;
            switch (sortBy) {
                case 'title':
                    comparison = a.title.localeCompare(b.title);
                    break;
                case 'artist':
                    comparison = a.artist.localeCompare(b.artist);
                    break;
                case 'radarNotes':
                case 'radarPeak':
                case 'radarTsumami':
                case 'radarTricky':
                case 'radarHandTrip':
                case 'radarOneHand':
                    comparison = a[sortBy] - b[sortBy];
                    break;
                default:
                    return 0;
            }
            return sortOrder === 'asc' ? comparison : -comparison;
        });
    }, [allCharts, sortBy, sortOrder]);

    useEffect(() => {
        setPage(1);
    }, [titleSearch, artistSearch, sortBy, sortOrder, difficulty]);

    useEffect(() => {
        const startIndex = (page - 1) * ITEMS_PER_PAGE;
        setDisplayedCharts(sortedCharts.slice(startIndex, startIndex + ITEMS_PER_PAGE));
    }, [sortedCharts, page]);

    const handleSortChange = (event) => {
        const newSortBy = event.target.value;
        if (newSortBy === sortBy) {
            setSortOrder(sortOrder === 'asc' ? 'desc' : 'asc');
        } else {
            setSortBy(newSortBy);
            setSortOrder('asc');
        }
    };

    const toggleSortOrder = () => {
        setSortOrder(sortOrder === 'asc' ? 'desc' : 'asc');
    };

    const handlePageChange = (event, value) => {
        setPage(value);
    };

    const totalPages = Math.ceil(sortedCharts.length / ITEMS_PER_PAGE);

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
        <Container maxWidth="xl" sx={{ py: 4 }}>
            <Typography variant="h4" component="h1" gutterBottom>
                Charts - Level {difficulty}
            </Typography>
            <Box sx={{ mb: 4 }}>
                <Grid container spacing={1} alignItems="center">
                    <Grid item xs={12} sm={6} md={4}>
                        <TextField
                            fullWidth
                            label="Search by Title"
                            variant="outlined"
                            value={titleSearch}
                            onChange={(e) => setTitleSearch(e.target.value)}
                        />
                    </Grid>
                    <Grid item xs={12} sm={6} md={4}>
                        <TextField
                            fullWidth
                            label="Search by Artist"
                            variant="outlined"
                            value={artistSearch}
                            onChange={(e) => setArtistSearch(e.target.value)}
                        />
                    </Grid>
                    <Grid item xs={12} md={4}>
                        <FormControl fullWidth>
                            <InputLabel id="sort-select-label">Sort By</InputLabel>
                            <Select
                                labelId="sort-select-label"
                                value={sortBy}
                                label="Sort By"
                                onChange={handleSortChange}
                            >
                                <MenuItem value="title">Title</MenuItem>
                                <MenuItem value="artist">Artist</MenuItem>
                                <MenuItem value="radarNotes">Notes</MenuItem>
                                <MenuItem value="radarPeak">Peak</MenuItem>
                                <MenuItem value="radarTsumami">Tsumami</MenuItem>
                                <MenuItem value="radarTricky">Tricky</MenuItem>
                                <MenuItem value="radarHandTrip">Hand Trip</MenuItem>
                                <MenuItem value="radarOneHand">One Hand</MenuItem>
                            </Select>
                        </FormControl>
                        <IconButton onClick={toggleSortOrder}>
                            {sortOrder === 'asc' ? <ArrowUpward /> : <ArrowDownward />}
                        </IconButton>
                    </Grid>
                </Grid>
            </Box>
            {error && (
                <Alert severity="error" sx={{ mb: 2 }}>
                    {error}
                </Alert>
            )}
            {loading ? (
                <CircularProgress sx={{ display: 'block', margin: '20px auto' }} />
            ) : (
                <>
                    <Grid container spacing={2}>
                        {displayedCharts.map((chart) => (
                            <Grid item xs={6} sm={4} md={3} lg={2} key={chart.id}>
                                <Card
                                    sx={{
                                        height: '100%',
                                        display: 'flex',
                                        flexDirection: 'column',
                                        cursor: 'pointer',
                                        backgroundColor: 'primary.light',
                                        '&:hover': {
                                            boxShadow: 6,
                                            transition: 'box-shadow 0.3s ease-in-out'
                                        }
                                    }}
                                    onClick={() => navigate(`/charts/${difficulty}/${chart.id}`)}
                                >
                                    <Box sx={{ position: 'relative', paddingTop: '100%', width: '100%' }}>
                                        <CardMedia
                                            component="img"
                                            image={chart.jacketLink}
                                            alt={chart.title}
                                            sx={{
                                                position: 'absolute',
                                                top: 0,
                                                left: 0,
                                                width: '100%',
                                                height: '100%',
                                                objectFit: 'cover'
                                            }}
                                        />
                                    </Box>
                                    <CardContent sx={{ flexGrow: 1, display: 'flex', flexDirection: 'column', p: 1 }}>
                                        <Box sx={{ mb: 1, overflow: 'hidden' }}>
                                            <Typography variant="body2" noWrap>{chart.title}</Typography>
                                            <Typography variant="caption" noWrap>{chart.artist}</Typography>
                                        </Box>
                                        <Box sx={{ flexGrow: 1, position: 'relative', minHeight: '200px' }}>
                                            <CenteredRadarChart
                                                chartData={{
                                                    labels: ['NOTES', 'PEAK', 'TSUMAMI', 'TRICKY', 'HAND\nTRIP', 'ONE\nHAND'],
                                                    datasets: [
                                                        {
                                                            data: [
                                                                chart.radarNotes,
                                                                chart.radarPeak,
                                                                chart.radarTsumami,
                                                                chart.radarTricky,
                                                                chart.radarHandTrip,
                                                                chart.radarOneHand
                                                            ],
                                                            borderColor: 'rgba(255, 255, 255, 0.6)',
                                                            borderWidth: 2,
                                                            fill: true,
                                                        },
                                                    ],
                                                }}
                                            />
                                        </Box>
                                    </CardContent>
                                </Card>
                            </Grid>
                        ))}
                    </Grid>
                    {sortedCharts.length > 0 ? (
                        <Box sx={{ display: 'flex', justifyContent: 'center', mt: 4 }}>
                            <Pagination
                                count={totalPages}
                                page={page}
                                onChange={handlePageChange}
                                color="primary"
                            />
                        </Box>
                    ) : (
                        <Typography variant="body1" sx={{ textAlign: 'center', mt: 4 }}>
                            No charts found matching your search criteria.
                        </Typography>
                    )}
                </>
            )}
        </Container>
    );
}

export default ChartListPage;