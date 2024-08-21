import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import {
    Container,
    Typography,
    Paper,
    Grid,
    Button,
    Card,
    CardMedia,
    Box
} from '@mui/material';
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
        <Box sx={{
            height: '100%',
            width: '100%',
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            paddingLeft: '70px',
            paddingRight: '50px',
            paddingBottom: '10px'
        }}>
            <Radar ref={chartRef} data={chartData} options={options} />
        </Box>
    );
}

function MusicDetailPage() {
    const [music, setMusic] = useState(null);
    const [charts, setCharts] = useState([]);
    const { id } = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        fetch(`/music/${id}`)
            .then(response => response.json())
            .then(data => setMusic(data))
            .catch(error => console.error('Error fetching music details:', error));

        fetch(`/chart/${id}`)
            .then(response => response.json())
            .then(data => setCharts(data))
            .catch(error => console.error('Error fetching charts:', error));
    }, [id]);

    const formatBPM = (bpm) => {
        return (bpm / 100).toFixed(2);
    };

    const formatDate = (dateNumber) => {
        if (!dateNumber) return 'N/A';
        const dateString = dateNumber.toString();
        if (dateString.length !== 8) return 'Invalid Date';
        const year = dateString.substring(0, 4);
        const month = dateString.substring(4, 6);
        const day = dateString.substring(6, 8);
        const date = new Date(year, month - 1, day);
        return date.toLocaleDateString('en-US', {
            year: 'numeric',
            month: 'long',
            day: 'numeric'
        });
    };

    if (!music) {
        return <Typography>Loading...</Typography>;
    }

    return (
        <Container maxWidth="lg">
            <Paper elevation={3} style={{ padding: '20px', marginTop: '20px', marginBottom: '20px' }}>
                <Grid container spacing={2}>
                    <Grid item xs={12} sm={4}>
                        {music.jacketLink && (
                            <Card>
                                <CardMedia
                                    component="img"
                                    sx={{
                                        width: '100%',
                                        aspectRatio: '1 / 1',
                                        objectFit: 'cover'
                                    }}
                                    image={music.jacketLink}
                                    alt={music.title}
                                />
                            </Card>
                        )}
                    </Grid>
                    <Grid item xs={12} sm={8}>
                        <Typography variant="h4" gutterBottom>{music.title}</Typography>
                        <Typography variant="h5" gutterBottom>{music.artist}</Typography>
                        <Grid container spacing={2}>
                            <Grid item xs={12} sm={6}>
                                <Typography><strong>ID:</strong> {music.id}</Typography>
                                <Typography><strong>Title Yomigana:</strong> {music.titleYomigana}</Typography>
                                <Typography><strong>Artist Yomigana:</strong> {music.artistYomigana}</Typography>
                                <Typography><strong>BPM:</strong> {
                                    music.bpmMin === music.bpmMax
                                        ? formatBPM(music.bpmMin)
                                        : `${formatBPM(music.bpmMin)} - ${formatBPM(music.bpmMax)}`
                                }</Typography>
                                <Typography><strong>Distribution Date:</strong> {formatDate(music.distributionDate)}</Typography>
                            </Grid>
                            <Grid item xs={12} sm={6}>
                                <Typography><strong>Background Number:</strong> {music.bgNo}</Typography>
                                <Typography><strong>Genre:</strong> {music.genre}</Typography>
                                <Typography><strong>Infinite Version:</strong> {music.infVer}</Typography>
                            </Grid>
                        </Grid>
                    </Grid>
                </Grid>
            </Paper>

            <Paper elevation={3} style={{ padding: '20px', marginTop: '20px' }}>
                <Typography variant="h5" gutterBottom>Charts</Typography>
                {charts.map((chart) => (
                    <Paper key={chart.id} elevation={2} style={{ padding: '15px', marginTop: '15px' }}>
                        <Grid container spacing={2}>
                            <Grid item xs={12} sm={3}>
                                {chart.jacketLink && (
                                    <Card>
                                        <CardMedia
                                            component="img"
                                            sx={{
                                                width: '100%',
                                                aspectRatio: '1 / 1',
                                                objectFit: 'cover'
                                            }}
                                            image={chart.jacketLink}
                                            alt={`${music.title} - ${chart.difficulty}`}
                                        />
                                    </Card>
                                )}
                            </Grid>
                            <Grid item xs={12} sm={5}>
                                <Typography variant="h6" gutterBottom><strong>{chart.difficulty} (Level {chart.level})</strong></Typography>
                                <Typography><strong>Illustrated By:</strong> {chart.illustratedBy}</Typography>
                                <Typography><strong>Effected By:</strong> {chart.effectedBy}</Typography>
                                <Typography><strong>Max Ex Score:</strong> {chart.maxExScore}</Typography>
                                <Typography><strong>Price:</strong> {chart.price}</Typography>
                                <Typography><strong>Limited:</strong> {chart.limited}</Typography>
                                <Typography><strong>Jacket Print:</strong> {chart.jacketPrint}</Typography>
                                <Typography><strong>Jacket Mask:</strong> {chart.jacketMask}</Typography>
                            </Grid>
                            <Grid item xs={12} sm={4}>
                                <Box sx={{ height: '250px', width: '90%', aspectRatio: '1 / 1' }}>
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
                            </Grid>
                        </Grid>
                    </Paper>
                ))}
            </Paper>

            <Button
                variant="contained"
                color="primary"
                style={{ marginTop: '20px', marginBottom: '20px' }}
                onClick={() => navigate('/music')}
            >
                Back to Music List
            </Button>
        </Container>
    );
}

export default MusicDetailPage;