function displaySearchResults(results) {
    searchResultsDiv.innerHTML = ''; // 결과를 표시하기 전에 이전 결과 삭제
    if (results.length === 0) {
        searchResultsDiv.innerHTML = '검색 결과가 없습니다.';
    } else {
        const ul = document.createElement('ul');
        results.forEach(function (result) {
            const li = document.createElement('li');
            li.textContent = result;
            li.addEventListener('click', function () {
                // 클릭한 검색어에 대한 페이지로 이동
                window.location.href = '/user/page?id=' + encodeURIComponent(result);
            });
            ul.appendChild(li);
        });
        searchResultsDiv.appendChild(ul);
    }
}