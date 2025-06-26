export function buildFileTree(files) {
  const root = {};

  for (const file of files) {
    const parts = file.path.split('/').filter(Boolean);
    let current = root;

    parts.forEach((part, index) => {
      if (!current[part]) {
        current[part] = {
          __data: {
            name: part,
            children: {},
            size: index === parts.length - 1 ? file.size : 0,
            isFile: index === parts.length - 1,
          },
        };
      } else if (index === parts.length - 1) {
        current[part].__data.size = file.size;
      }

      current = current[part].__data.children;
    });
  }

  return root;
}
