import React from 'react';
import { TreeView, TreeItem } from '@mui/lab';
import { Folder, Description } from '@mui/icons-material';

function renderTree(node, path = '') {
  return Object.entries(node).map(([key, value]) => {
    const { name, children, size, isFile } = value.__data;
    const fullPath = `${path}/${name}`;
    return (
      <TreeItem
        key={fullPath}
        nodeId={fullPath}
        label={`${name} (${(size / 1024).toFixed(1)} KB)`}
        icon={isFile ? <Description /> : <Folder />}
      >
        {!isFile && renderTree(children, fullPath)}
      </TreeItem>
    );
  });
}

export default function FileTreeView({ treeData }) {
  return (
    <TreeView
      defaultCollapseIcon={<Folder />}
      defaultExpandIcon={<Folder />}
      sx={{ height: '100%', flexGrow: 1, overflowY: 'auto' }}
    >
      {renderTree(treeData)}
    </TreeView>
  );
}
