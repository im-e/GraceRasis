import React, { useState } from 'react';
import { Input, Select, Button } from '@/components/ui/input';
import { Card, CardHeader, CardContent, CardTitle } from '@/components/ui/card';
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '@/components/ui/table';

const Search = () => {
    const [searchType, setSearchType] = useState('chart');
    const [searchParams, setSearchParams] = useState({
        level: '',
        musicId: '',
        title: '',
        artist: '',
    });
    const [results, setResults] = useState({ charts: [], music: [] });

    const handleInputChange = (e) => {
        setSearchParams({ ...searchParams, [e.target.name]: e.target.value });
    };

    const handleSearch = async () => {
        let response;
        if (searchType === 'chart') {
            if (searchParams.level) {
                response = await fetch(`/api/charts?level=${searchParams.level}`);
            } else if (searchParams.musicId) {
                response = await fetch(`/api/charts?musicId=${searchParams.musicId}`);
            }
            const data = await response.json();
            setResults({ ...results, charts: data });
        } else {
            let url = '/api/music?';
            if (searchParams.title) url += `title=${searchParams.title}&`;
            if (searchParams.artist) url += `artist=${searchParams.artist}`;
            response = await fetch(url);
            const data = await response.json();
            setResults({ ...results, music: data });
        }
    };

    return (
        <Card className="w-full max-w-4xl mx-auto">
            <CardHeader>
                <CardTitle>Sound Voltex Search</CardTitle>
            </CardHeader>
            <CardContent>
                <div className="space-y-4">
                    <Select
                        value={searchType}
                        onChange={(e) => setSearchType(e.target.value)}
                        className="w-full"
                    >
                        <option value="chart">Search Charts</option>
                        <option value="music">Search Music</option>
                    </Select>

                    {searchType === 'chart' && (
                        <div className="space-y-2">
                            <Input
                                name="level"
                                placeholder="Chart Level"
                                value={searchParams.level}
                                onChange={handleInputChange}
                            />
                            <Input
                                name="musicId"
                                placeholder="Music ID"
                                value={searchParams.musicId}
                                onChange={handleInputChange}
                            />
                        </div>
                    )}

                    {searchType === 'music' && (
                        <div className="space-y-2">
                            <Input
                                name="title"
                                placeholder="Music Title"
                                value={searchParams.title}
                                onChange={handleInputChange}
                            />
                            <Input
                                name="artist"
                                placeholder="Artist"
                                value={searchParams.artist}
                                onChange={handleInputChange}
                            />
                        </div>
                    )}

                    <Button onClick={handleSearch} className="w-full">
                        Search
                    </Button>

                    {results.charts.length > 0 && (
                        <Table>
                            <TableHeader>
                                <TableRow>
                                    <TableHead>Chart ID</TableHead>
                                    <TableHead>Music ID</TableHead>
                                    <TableHead>Level</TableHead>
                                </TableRow>
                            </TableHeader>
                            <TableBody>
                                {results.charts.map((chart) => (
                                    <TableRow key={chart.id}>
                                        <TableCell>{chart.id}</TableCell>
                                        <TableCell>{chart.musicId}</TableCell>
                                        <TableCell>{chart.level}</TableCell>
                                    </TableRow>
                                ))}
                            </TableBody>
                        </Table>
                    )}

                    {results.music.length > 0 && (
                        <Table>
                            <TableHeader>
                                <TableRow>
                                    <TableHead>Music ID</TableHead>
                                    <TableHead>Title</TableHead>
                                    <TableHead>Artist</TableHead>
                                </TableRow>
                            </TableHeader>
                            <TableBody>
                                {results.music.map((music) => (
                                    <TableRow key={music.id}>
                                        <TableCell>{music.id}</TableCell>
                                        <TableCell>{music.title}</TableCell>
                                        <TableCell>{music.artist}</TableCell>
                                    </TableRow>
                                ))}
                            </TableBody>
                        </Table>
                    )}
                </div>
            </CardContent>
        </Card>
    );
};

export default Search;