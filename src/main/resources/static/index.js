document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('shortenerForm');
    const resultSection = document.getElementById('resultSection');
    const originalUrlTextbox = document.getElementById('originalUrlTextbox');
    const shortUrlTextbox = document.getElementById('shortUrlTextbox');
    const copyButton = document.getElementById('copyButton');

    // Handle Form submission
    form.addEventListener('submit', (e) => {
        e.preventDefault();
        const rawUrl = document.getElementById('urlInput').value;

        // Display original URL
        originalUrlTextbox.value = rawUrl;
        
        fetch('http://localhost:8080/api/v1/url/shorten', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ url: rawUrl })
        })
        .then(response => {
            return response.text();
            console.log("response is"+response.text())
        })
        .then(data => {
            console.log("Data is " +data);
            shortUrlTextbox.value = data;
        }).catch(error => {
            console.log("Error: " + error);
            alert('Failed to shorten URL');
        });

        // Show result section
        resultSection.classList.remove('hidden');
    });

    // Copy to clipboard functionality
    copyButton.addEventListener('click', () => {
        navigator.clipboard.writeText(shortUrlTextbox.value).then(() => {
            // Update button feedback
            const originalText = copyButton.textContent;
            copyButton.textContent = 'Copied!';
            copyButton.classList.add('copied');
            
            // Revert after 2 seconds
            setTimeout(() => {
                copyButton.textContent = originalText;
                copyButton.classList.remove('copied');
            }, 2000);
        }).catch(() => {
            alert('Failed to copy to clipboard');
        });
    });
});