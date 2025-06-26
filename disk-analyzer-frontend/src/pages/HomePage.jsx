import React, { useState } from 'react';
import FolderPathInput from '../components/FolderPathInput';
import { startScan } from '../api/scanApi';
import { Box, Typography, Alert } from '@mui/material';

export default function HomePage() {
  const [message, setMessage] = useState(null);

  const handleScan = async (folderPath) => {
    setMessage(null);
    try {
      const response = await startScan(folderPath);
      setMessage({ type: 'success', text: `Scan started! Scan ID: ${response.data.scanId}` });
    } catch (err) {
      setMessage({ type: 'error', text: 'Failed to start scan: ' + (err.response?.data?.message || err.message) });
    }
  };

  return (
    <Box p={4}>
      <Typography variant="h4" gutterBottom>
        Disk Analyzer - Start New Scan
      </Typography>

      <FolderPathInput onScan={handleScan} />

      {message && (
        <Alert severity={message.type} sx={{ mt: 2 }}>
          {message.text}
        </Alert>
      )}
    </Box>
  );
}
