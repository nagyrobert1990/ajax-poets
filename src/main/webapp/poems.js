let poemsTableEl;
let poemsTableBodyEl;


function onPoemClicked() {
    const poemId = this.dataset.poemId;

    const params = new URLSearchParams();
    params.append('id', poemId);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onPoemResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('GET', 'protected/poem?' + params.toString());
    xhr.send();

}

function appendPoem(poem) {
    const idTdEl = document.createElement('td');
    idTdEl.textContent = poem.id;

    const titleaEl = document.createElement('a');
    titleaEl.textContent = poem.title;
    titleaEl.href = 'javascript:void(0);';
    titleaEl.dataset.poemId = poem.id;
    titleaEl.addEventListener('click', onPoemClicked);

    const titleTdEl = document.createElement('td');
    titleTdEl.appendChild(titleaEl);


    const dateTdEl = document.createElement('td');
    dateTdEl.textContent = poem.publishedDate;


    const trEl = document.createElement('tr');
    trEl.appendChild(idTdEl);
    trEl.appendChild(titleTdEl);
    trEl.appendChild(dateTdEl);

    poemsTableBodyEl.appendChild(trEl);
}

function appendPoems(poems) {
    removeAllChildren(poemsTableBodyEl);

    for (let i = 0; i < poems.length; i++) {
        const poem = poems[i];
        appendPoem(poem);
    }
}

function onPoemsLoad(poems) {
    poemsTableEl = document.getElementById('poems');
    poemsTableBodyEl = poemsTableEl.querySelector('tbody');
    appendPoems(poems);
}

function onPoemsResponse() {
    if (this.status === OK) {
        showContents(['profile-content', 'poems-content', 'logout-content']);
        onPoemsLoad(JSON.parse(this.responseText));
    } else {
        onOtherResponse(poemsContentDivEl, this);
    }
