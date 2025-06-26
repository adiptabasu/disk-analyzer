import React, { useState, useRef } from 'react';

export default function FolderPathInput({ onScan }) {
  const [folderPath, setFolderPath] = useState('');
  const fileInputRef = useRef(null);

  const handlePickFolderClick = () => {
    if (fileInputRef.current) {
      fileInputRef.current.value = null;
      fileInputRef.current.click();
    }
  };

  const handleFolderSelected = (event) => {
    const files = event.target.files;
    if (files.length > 0) {
      const firstPath = files[0].webkitRelativePath || '';
      const folderName = firstPath.split('/')[0];
      setFolderPath(folderName);
    }
  };

  return (
    <div>
      <input
        type="text"
        placeholder="Enter full folder path, e.g. /home/user/docs"
        value={folderPath}
        onChange={(e) => setFolderPath(e.target.value)}
        style={{ width: 300, marginRight: 10, padding: 6 }}
      />
      <button onClick={handlePickFolderClick}>Pick Folder</button>

      <input
        type="file"
        ref={fileInputRef}
        style={{ display: 'none' }}
        webkitdirectory="true"
        directory="true"
        multiple
        onChange={handleFolderSelected}
      />

      <button
        onClick={() => {
          if (folderPath.trim() === '') {
            alert('Please enter or pick a folder path first');
            return;
          }
          onScan(folderPath.trim());
        }}
        style={{ marginLeft: 10, padding: '6px 12px' }}
      >
        Scan
      </button>
    </div>
  );
}
