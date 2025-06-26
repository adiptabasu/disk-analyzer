import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/scan';

export const startBasicScan = (path) =>
  axios.post(`${API_BASE_URL}/start`, { path });

export const startDetailedScan = (path) =>
  axios.post(`${API_BASE_URL}/detailed/start`, { path });

export const getScanStatus = (scanId) =>
  axios.get(`${API_BASE_URL}/status/${scanId}`);

export const cancelScan = (scanId) =>
  axios.delete(`${API_BASE_URL}/cancel/${scanId}`);

export const getScanHistory = () =>
  axios.get(`${API_BASE_URL}/history`);

export const getScanDetails = (scanId) =>
  axios.get(`${API_BASE_URL}/details/${scanId}`);
