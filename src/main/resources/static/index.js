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

        // Populate fields
        originalUrlTextbox.value = rawUrl;
        
        fetch('localhost:8080/api/url/shorten', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ url: rawUrl })
        })
        .then(response => response.json())
        .then(data => {
            shortUrlTextbox.value = data.shortUrl;
        }).catch(error => {
            console.log("error"+ error);
        });
        


        // Unhidden result structure
        resultSection.classList.remove('hidden');
    });

    // Quick Clipboard Copy Action
    copyButton.addEventListener('click', () => {
        navigator.clipboard.writeText(shortUrlTextbox.value).then(() => {
            // Update UI status to feedback successfully
            copyButton.textContent = 'Copied!';
            copyButton.classList.replace('text-slate-600', 'text-emerald-600');
            copyButton.classList.replace('border-slate-200', 'border-emerald-200');
            
            // Revert state back after a short duration
            setTimeout(() => {
                copyButton.textContent = 'Copy';
                copyButton.classList.replace('text-emerald-600', 'text-slate-600');
                copyButton.classList.replace('border-emerald-200', 'border-slate-200');
            }, 2000);
        });
    });
});