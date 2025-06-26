# Disk Analyzer Application

A full-stack disk space analyzer tool that scans directories and displays disk usage in an interactive UI.  
The project includes a **Spring Boot backend API** for scanning and managing disk usage data asynchronously, and a **React + Vite frontend** that provides a modern, user-friendly interface to view scan results, monitor progress, and manage scan history.

---

## Table of Contents

- [Project Overview](#project-overview)
- [Architecture](#architecture)
- [Backend (Spring Boot)](#backend-spring-boot)
  - [Features](#features)
  - [Technology Stack](#technology-stack)
  - [Design Details](#design-details)
  - [Setup and Running](#setup-and-running-backend)
  - [API Documentation](#api-documentation)
- [Frontend (React + Vite)](#frontend-react--vite)
  - [Features](#features-1)
  - [Technology Stack](#technology-stack-1)
  - [Design Details](#design-details-frontend)
  - [Setup and Running](#setup-and-running-frontend)
- [Usage Instructions](#usage-instructions)
- [Limitations and Notes](#limitations-and-notes)
- [Future Improvements](#future-improvements)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

---

## Project Overview

Disk Analyzer is designed to provide detailed insight into disk usage on your system. It scans directories and subdirectories, aggregates file sizes, and presents the data clearly, enabling you to identify large files or folders that consume disk space.

It supports:

- Cross-platform scanning (Windows, Linux, macOS)
- Asynchronous scanning to prevent blocking
- Real-time progress updates
- Scan cancellation
- Persistent scan history with metadata
- Interactive frontend with folder tree visualization

---

## Architecture

The project is split into two main components:

1. **Backend:**  
   - Built with Spring Boot 3.x  
   - Provides RESTful APIs for scanning operations, status tracking, cancellation, and history management.  
   - Performs file system scanning asynchronously using Java concurrency features.  
   - Maintains in-memory storage for scan results and history.

2. **Frontend:**  
   - Built with React 18+ and Vite for fast bundling and development experience.  
   - Uses Material-UI (MUI) for UI components and styling.  
   - Consumes backend REST APIs via Axios.  
   - Manages routing with React Router.  
   - Provides user-friendly controls for folder input, scan initiation, progress monitoring, and viewing scan history.

---

## Backend (Spring Boot)

### Features

- **Start Scan:** Submit a directory path to begin scanning asynchronously.
- **Real-Time Progress:** Retrieve current scan progress and partial results.
- **Cancel Scan:** Ability to cancel an ongoing scan gracefully.
- **Detailed vs Summary Scans:** Supports both detailed full directory tree scans and top-level summaries.
- **Scan History:** Retrieve metadata and results of past scans.
- **Cross-Platform:** Works on Windows, Linux, and macOS without any platform-specific dependencies.

### Technology Stack

- Java 24
- Spring Boot 3.5.3
- Spring Web (REST APIs)
- Spring Async (`@Async`) for non-blocking scans
- Maven for build and dependency management

### Design Details

- **Asynchronous Scanning:** Uses Spring's `@Async` to run scans in background threads, returning scan IDs immediately.
- **In-Memory Scan Storage:** Uses thread-safe maps to keep track of scan status, results, and cancellation tokens.
- **Scan Cancellation:** Implements cancellation tokens to stop scanning mid-way if requested.
- **Partial Results:** Supports polling for current scan progress and partial folder size data.
- **Data Models:** Uses POJOs to represent folder/file nodes, scan metadata, and results.

### Setup and Running Backend

1. **Prerequisites:**  
   - Java 24+ installed and configured  
   - Maven installed  

2. **Clone the repo and navigate to backend:**  
   ```bash
   git clone https://github.com/yourusername/disk-analyzer.git
   cd disk-analyzer/disk-analyzer-backend
