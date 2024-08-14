import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import {Container, Typography, Paper, Grid, Button, Card, CardMedia} from '@mui/material';

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
                                    height="300"
                                    image={music.jacketLink}
                                    alt={music.title}
                                />
                            </Card>
                        )}
                    </Grid>
                    <Grid item xs={12} sm={8}>
                        <Typography variant="h4" gutterBottom>{music.title}</Typography>
                        <Typography variant="h5" gutterBottom>{music.artist}</Typography>
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
                                            height="200"
                                            image={chart.jacketLink}
                                            alt={`${music.title} - ${chart.difficulty}`}
                                        />
                                    </Card>
                                )}
                            </Grid>
                            <Grid item xs={12} sm={9}>
                                <Typography variant="h6" gutterBottom><strong>{chart.difficulty} (Level {chart.level})</strong></Typography>
                                <Grid container spacing={2}>
                                    <Grid item xs={12} sm={6}>
                                        <Typography><strong>Illustrated By:</strong> {chart.illustratedBy}</Typography>
                                        <Typography><strong>Effected By:</strong> {chart.effectedBy}</Typography>
                                        <Typography><strong>Max Ex Score:</strong> {chart.maxExScore}</Typography>
                                        <Typography><strong>Price:</strong> {chart.price}</Typography>
                                        <Typography><strong>Limited:</strong> {chart.limited}</Typography>
                                        <Typography><strong>Jacket Print:</strong> {chart.jacketPrint}</Typography>
                                        <Typography><strong>Jacket Mask:</strong> {chart.jacketMask}</Typography>
                                    </Grid>
                                    <Grid item xs={12} sm={6}>
                                        <Typography><strong>Radar Notes:</strong> {chart.radarNotes}</Typography>
                                        <Typography><strong>Radar Peak:</strong> {chart.radarPeak}</Typography>
                                        <Typography><strong>Radar Tsumami:</strong> {chart.radarTsumami}</Typography>
                                        <Typography><strong>Radar Tricky:</strong> {chart.radarTricky}</Typography>
                                        <Typography><strong>Radar Hand Trip:</strong> {chart.radarHandTrip}</Typography>
                                        <Typography><strong>Radar One Hand:</strong> {chart.radarOneHand}</Typography>
                                    </Grid>
                                </Grid>
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