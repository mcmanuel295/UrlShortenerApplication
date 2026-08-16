document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('shortenerForm');
    const resultSection = document.getElementById('resultSection');
    const originalUrlDisplay = document.getElementById('originalUrlDisplay');
    const shortUrlDisplay = document.getElementById('shortUrlDisplay');
    const shortCodeDisplay = document.getElementById('shortCodeDisplay');

    // Handle Form submission
    form.addEventListener('submit', (e) => {
        e.preventDefault();
        const rawUrl = document.getElementById('urlInput').value;

        // Display original URL
        originalUrlDisplay.textContent = rawUrl;
        
        fetch('http://localhost:8080/api/v1/url/shorten', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ url: rawUrl })
        })
        .then(response => response.json())
        .then(data => {
            shortUrlDisplay.textContent = data.shortUrl;
            shortUrlDisplay.href = data.shortUrl;
            // Extract short code from the response
            const shortCode = data.shortUrl.split('/').pop();
            shortCodeDisplay.textContent = shortCode;
        }).catch(error => {
            console.log("Error: " + error);
            alert('Failed to shorten URL');
        });

        // Show result section
        resultSection.classList.remove('hidden');
    });
});