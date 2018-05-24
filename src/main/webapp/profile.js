function onPoemsLoaded() {
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onPoemsResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('GET', 'protected/poems');
    xhr.send();
}

function onProfileLoad(poet) {
    clearMessages();
    showContents(['profile-content', 'poems-content', 'logout-content']);

    const poetNameSpandEl = document.getElementById('poet-name');
    poetNameSpandEl.textContent = poet.name;
    onPoemsLoaded();
}