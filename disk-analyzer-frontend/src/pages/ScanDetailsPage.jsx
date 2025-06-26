import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getScanDetails } from '../api/scanApi';
import { buildFileTree } from '../utils/buildFileTree';
import FileTreeView from '../components/FileTreeView';
import { Box, Typography, CircularProgress } from '@mui/material';

export default function ScanDetailsPage() {
  const { scanId } = useParams();
  const [treeData, setTreeData] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    async function fetchDetails() {
      try {
        const res = await getScanDetails(scanId);
        setTreeData(buildFileTree(res.data.allFiles || []));
      } catch {
        setTreeData(null);
      } finally {
        setLoading(false);
      }
    }
    fetchDetails();
  }, [scanId]);

  return (
    <Box p={4}>
      <Typography variant="h4" gutterBottom>
        Scan Details: {scanId}
      </Typography>
      {loading ? (
        <CircularProgress />
      ) : treeData ? (
        <Box
          sx={{ height: '600px', border: '1px solid #ddd', borderRadius: 1, p: 1 }}
        >
          <FileTreeView treeData={treeData} />
        </Box>
      ) : (
        <Typography>Failed to load scan details.</Typography>
      )}
    </Box>
  );
}
