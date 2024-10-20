# Image-search-engine
Overview
This is a simple image search engine built using HTML, CSS, and JavaScript. The app fetches images from the Unsplash API based on user queries and displays them in a grid format. Users can search for images and load more results using a "Show More" button.

Features
Image Search: Users can search for images using keywords.
Pagination: Load more images with a "Show More" button for endless scrolling.
Responsive Design: The layout adapts to various screen sizes, making it mobile-friendly.
API Integration: Fetch images dynamically from the Unsplash API.
Technologies Used
HTML5 for the structure.
CSS3 for styling, including a background image and grid layout.
JavaScript (Vanilla) for fetching images from the Unsplash API and handling events.
Unsplash API for retrieving high-quality images.
How to Use
Clone the repository.
Open index.html in your browser.
Enter a search query in the input field and click "Search" to display images.
Use the "Show More" button to load additional results.
API Key
This project uses the Unsplash API. You will need an Unsplash API key to run the project. Replace the API key in script.js with your own key:

javascript
Copy code
const UNSPLASH_API_KEY = 'your-api-key';
Customization
You can modify the number of images fetched per request by changing the per_page parameter in the API URL within script.js.
