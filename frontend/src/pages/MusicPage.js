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
    ToggleButton,
    ToggleButtonGroup
} from '@mui/material';
import { ArrowUpward, ArrowDownward } from '@mui/icons-material';
import { useNavigate } from 'react-router-dom';
import LevelBox from '../components/LevelBox';
import { debounce } from 'lodash';

const ITEMS_PER_PAGE = 24;

function MusicPage() {
    const [allMusic, setAllMusic] = useState([]);
    const [displayedMusic, setDisplayedMusic] = useState([]);
    const [titleSearch, setTitleSearch] = useState('');
    const [artistSearch, setArtistSearch] = useState('');
    const [levelSearch, setLevelSearch] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [page, setPage] = useState(1);
    const [sortBy, setSortBy] = useState('title');
    const [sortOrder, setSortOrder] = useState('asc');
    const navigate = useNavigate();

    const fetchMusic = useCallback(async () => {
        setLoading(true);
        setError(null);
        try {
            let url;
            if (levelSearch !== null) {
                url = `/music/level/${levelSearch}`;
            } else {
                url = '/music';
                const params = new URLSearchParams();
                if (titleSearch) params.append('title', titleSearch);
                if (artistSearch) params.append('artist', artistSearch);
                if (params.toString()) {
                    url += `?${params.toString()}`;
                }
            }

            const response = await fetch(url);
            if (!response.ok) {
                throw new Error('Failed to fetch music data');
            }
            const data = await response.json();
            setAllMusic(Array.isArray(data) ? data : []);
        } catch (error) {
            console.error('Error fetching music:', error);
            setError('Failed to load music data. Please try again later.');
            setAllMusic([]);
        }
        setLoading(false);
    }, [titleSearch, artistSearch, levelSearch]);

    const debouncedFetchMusic = useMemo(
        () => debounce(fetchMusic, 300),
        [fetchMusic]
    );

    useEffect(() => {
        debouncedFetchMusic();
        return () => debouncedFetchMusic.cancel();
    }, [debouncedFetchMusic]);

    const sortedMusic = useMemo(() => {
        return [...allMusic].sort((a, b) => {
            let comparison = 0;
            switch (sortBy) {
                case 'title':
                    comparison = a.title.localeCompare(b.title);
                    break;
                case 'artist':
                    comparison = a.artist.localeCompare(b.artist);
                    break;
                case 'distributionDate':
                    comparison = new Date(a.distributionDate) - new Date(b.distributionDate);
                    break;
                default:
                    return 0;
            }
            return sortOrder === 'asc' ? comparison : -comparison;
        });
    }, [allMusic, sortBy, sortOrder]);

    useEffect(() => {
        setPage(1);  // Reset to page 1 when search criteria or sort options change
    }, [titleSearch, artistSearch, levelSearch, sortBy, sortOrder]);

    useEffect(() => {
        const startIndex = (page - 1) * ITEMS_PER_PAGE;
        setDisplayedMusic(sortedMusic.slice(startIndex, startIndex + ITEMS_PER_PAGE));
    }, [sortedMusic, page]);

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

    const handleLevelChange = (event, newLevel) => {
        setLevelSearch(newLevel);
    };

    const levelColors = {
        novice: '#B255F9',
        advanced: '#FFFF1A',
        exhaust: '#D04243',
    };

    const totalPages = Math.ceil(sortedMusic.length / ITEMS_PER_PAGE);

    return (
        <Container maxWidth="xl" sx={{ py: 4 }}>
            <Typography variant="h4" component="h1" gutterBottom>
                All Music
            </Typography>
            <Box sx={{ mb: 4 }}>
                <Grid container spacing={2} alignItems="center">
                    <Grid item xs={12}>
                        <Box sx={{ display: 'flex', justifyContent: 'center', flexWrap: 'wrap', gap:1 }}>
                            <ToggleButtonGroup
                                value={levelSearch}
                                exclusive
                                onChange={handleLevelChange}
                                aria-label="level search"
                            >
                                {[...Array(20)].map((_, index) => (
                                    <ToggleButton
                                        key={index + 1}
                                        value={index + 1}
                                        aria-label={`Level ${index + 1}`}
                                    >
                                        {index + 1}
                                    </ToggleButton>
                                ))}
                            </ToggleButtonGroup>
                        </Box>
                    </Grid>
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
                                <MenuItem value="distributionDate">Distribution Date</MenuItem>
                            </Select>
                        </FormControl>
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
                        {displayedMusic.map((music) => (
                            <Grid item xs={6} sm={4} md={3} lg={2} key={music.id}>
                                <Card
                                    sx={{
                                        height: '100%',
                                        display: 'flex',
                                        flexDirection: 'column',
                                        cursor: 'pointer',
                                        '&:hover': {
                                            boxShadow: 6,
                                            transition: 'box-shadow 0.3s ease-in-out'
                                        }
                                    }}
                                    onClick={() => navigate(`/music/${music.id}`)}
                                >
                                    <Box sx={{ position: 'relative', paddingTop: '100%', width: '100%' }}>
                                        <CardMedia
                                            component="img"
                                            image={music.jacketLink}
                                            alt={music.title}
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
                                    <CardContent sx={{ flexGrow: 1 }}>
                                        <Box sx={{ display: 'flex', justifyContent: 'space-between', mb: 1 }}>
                                            <LevelBox level={music.noviceLevel} color={levelColors.novice} />
                                            <LevelBox level={music.advancedLevel} color={levelColors.advanced} />
                                            <LevelBox level={music.exhaustLevel} color={levelColors.exhaust} />
                                            {music.finalLevel > 0 && (
                                                <LevelBox
                                                    level={music.finalLevel}
                                                    difficulty={music.finalDifficulty}
                                                />
                                            )}
                                        </Box>
                                        <Typography gutterBottom variant="body1" component="div" noWrap>
                                            {music.title}
                                        </Typography>
                                        <Typography variant="body2" color="text.secondary" noWrap>
                                            {music.artist}
                                        </Typography>
                                    </CardContent>
                                </Card>
                            </Grid>
                        ))}
                    </Grid>
                    {sortedMusic.length > 0 ? (
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
                            No music found matching your search criteria.
                        </Typography>
                    )}
                </>
            )}
        </Container>
    );
}

export default MusicPage;