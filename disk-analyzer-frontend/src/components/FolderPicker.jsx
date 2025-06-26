import React from 'react';
import { Button, Typography } from '@mui/material';

export default function FolderPicker({ onSelect }) {
  const handleChange = (e) => {
    // Files selected in folder picker
    const files = e.target.files;
    if (files.length === 0) return;

    // Derive common folder path prefix from files
    // Limited because browser sandboxing blocks full absolute path
    const firstFilePath = files[0].webkitRelativePath || files[0].name;
    const folderName = firstFilePath.split('/')[0];
    onSelect(folderName);
  };

  return (
    <>
      <input
        type="file"
        id="folder-picker"
        webkitdirectory="true"
        directory=""
        multiple
        style={{ display: 'none' }}
        onChange={handleChange}
      />
      <label htmlFor="folder-picker">
        <Button variant="outlined" component="span">
          Pick Folder (browser sandboxed)
        </Button>
      </label>
      <Typography variant="caption" sx={{ ml: 2, color: 'text.secondary' }}>
        *Folder picker is limited to browser sandboxed files; please use manual path input for scanning full system paths.
      </Typography>
    </>
  );
}
