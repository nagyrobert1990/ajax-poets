function searchSubstring(numberOfSubstring) {
    const numberOfSubstringEl = document.getElementById('numberOfSubstring');
    numberOfSubstringEl.textContent = numberOfSubstring;
}

function onSearchResponse() {
    clearMessages();
    if (this.status === OK) {
        searchSubstring(JSON.parse(this.responseText));
    } else {
        const errorDivEl = document.getElementById('error');
        onOtherResponse(errorDivEl, this);
    }
}

function onSearchClicked() {
    const seekerForm = document.forms['seeker'];

    const subString = seekerForm.querySelector('input[name="substring"]');
    const subStringValue = subString.value;

    const params = new URLSearchParams();
    params.append('substring', subStringValue);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onSearchResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('POST', 'protected/poem');
    xhr.send(params);
    }

function onPoemLoad(poem) {
    const poemContentSpanEl = document.getElementById('contentofpoem');
    poemContentSpanEl.textContent = poem.content;
}


function onPoemResponse() {
    if (this.status === OK) {
        showContents(['profile-content', 'poems-content','poem-content', 'logout-content']);
        onPoemLoad(JSON.parse(this.responseText));
    } else {
        onOtherResponse(poemContentDivEl, this);
    }
}