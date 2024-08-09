import React, { useState, useEffect } from 'react';
import {
    Container,
    Typography,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Paper,
    Card, CardMedia
} from '@mui/material';
import { useNavigate } from 'react-router-dom';

function MusicPage() {
    const [musicList, setMusicList] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetch('/music/all')
            .then(response => response.json())
            .then(data => setMusicList(data))
            .catch(error => console.error('Error fetching music:', error));
    }, []);

    const formatBPM = (bpm) => {
        return (bpm / 100);
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

    const handleRowClick = (musicId) => {
        navigate(`/music/${musicId}`);
    };

    return (
        <Container maxWidth="lg">
            <Typography variant="h4" component="h1" gutterBottom>
                All Music
            </Typography>
            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Jacket</TableCell>
                            <TableCell>Title</TableCell>
                            <TableCell>Artist</TableCell>
                            <TableCell>BPM</TableCell>
                            <TableCell>Genre</TableCell>
                            <TableCell>Distribution Date</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {musicList.map((music) => (
                            <TableRow
                                key={music.id}
                                onClick={() => handleRowClick(music.id)}
                                style={{ cursor: 'pointer' }}
                                hover
                            >
                                <TableCell>
                                    {music.jacketLink && (
                                        <Card style={{ width: '50px', height: '50px' }}>
                                            <CardMedia
                                                component="img"
                                                height="50"
                                                image={music.jacketLink}
                                                alt={music.title}
                                            />
                                        </Card>
                                    )}
                                </TableCell>
                                <TableCell>{music.title}</TableCell>
                                <TableCell>{music.artist}</TableCell>
                                <TableCell>
                                    {music.bpmMin === music.bpmMax
                                        ? formatBPM(music.bpmMin)
                                        : `${formatBPM(music.bpmMin)} - ${formatBPM(music.bpmMax)}`
                                    }
                                </TableCell>
                                <TableCell>{music.genre}</TableCell>
                                <TableCell>{formatDate(music.distributionDate)}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Container>
    );
}

export default MusicPage;