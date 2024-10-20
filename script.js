const searchBtn = document.getElementById('searchBtn');
const searchInput = document.getElementById('searchInput');
const imageGrid = document.getElementById('imageGrid');
const showMoreBtn = document.getElementById('show-more-btn');

let query = '';   // to store the search query
let page = 1;     // to manage pagination for fetching more images

// Unsplash API Key 
const UNSPLASH_API_KEY = 'MG-eqf91RGFtIav-0Ei5OszLwTibMfO4kjzxDaXaD_M';
const UNSPLASH_URL = 'https://api.unsplash.com/search/photos';

// Function to fetch images
function fetchImages() {
    const url = `${UNSPLASH_URL}?query=${query}&page=${page}&client_id=${UNSPLASH_API_KEY}&per_page=12`;

    fetch(url)
        .then(response => response.json())
        .then(data => {
            displayImages(data.results);

            // Display "Show More" button if more images are available
            if (data.total_pages > page) {
                showMoreBtn.style.display = 'block';
            } else {
                showMoreBtn.style.display = 'none';
            }
        })
        .catch(error => {
            console.error('Error fetching images:', error);
        });
}

// Function to display images in the grid
function displayImages(images) {
    images.forEach(image => {
        const imgElement = document.createElement('img');
        imgElement.src = image.urls.small;
        imgElement.alt = image.alt_description || 'Image';
        imageGrid.appendChild(imgElement);
    });
}

// event listener for search button
searchBtn.addEventListener('click', () => {
    query = searchInput.value.trim();
    if (query !== "") {
        // Reset the image grid and page count
        imageGrid.innerHTML = '';
        page = 1;
        fetchImages();
    }
});

//  event listener for Show more button
showMoreBtn.addEventListener('click', () => {
    page++;
    fetchImages();
});
