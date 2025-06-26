import React, { useEffect, useState } from 'react';
import { getScanHistory } from '../api/scanApi';
import {
  Box,
  Typography,
  List,
  ListItemButton,
  ListItemText,
  CircularProgress,
} from '@mui/material';
import { useNavigate } from 'react-router-dom';

export default function HistoryPage() {
  const [history, setHistory] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    async function fetchHistory() {
      try {
        const res = await getScanHistory();
        setHistory(res.data);
      } catch {
        setHistory([]);
      } finally {
        setLoading(false);
      }
    }
    fetchHistory();
  }, []);

  return (
    <Box p={4}>
      <Typography variant="h4" gutterBottom>
        Scan History
      </Typography>
      {loading ? (
        <CircularProgress />
      ) : history.length === 0 ? (
        <Typography>No scans found.</Typography>
      ) : (
        <List>
          {history.map((scan) => (
            <ListItemButton
              key={scan.scanId}
              onClick={() => navigate(`/scan/${scan.scanId}`)}
            >
              <ListItemText
                primary={`${scan.path} - ${scan.status}`}
                secondary={`Started at: ${new Date(scan.timestamp).toLocaleString()}`}
              />
            </ListItemButton>
          ))}
        </List>
      )}
    </Box>
  );
}
