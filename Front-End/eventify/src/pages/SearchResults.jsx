import { useLocation, Link } from "react-router-dom";
import { useEffect, useState } from "react";
import searchData from "../dataTest/searchData";



function useQuery() {
  return new URLSearchParams(useLocation().search);
}

export default function SearchResults() {
  const query = useQuery().get("query");
  const [results, setResults] = useState([]);

  useEffect(() => {
    if (query) {
      const lower = query.toLowerCase();
      const matches = searchData.filter(item =>
        item.title?.toLowerCase().includes(lower) ||
        item.description?.toLowerCase().includes(lower)
      );
      setResults(matches);
    }
  }, [query]);

  return (
    <div className="container py-4">
      <h3>Search Results for: "{query}"</h3>
      {results.length === 0 ? (
        <p>No results found.</p>
      ) : (
        <div className="list-group">
          {results.map(item => (
            <Link
              key={item.id}
              to={item.link}
              className="list-group-item list-group-item-action"
            >
              <h5 className="mb-1">{item.title}</h5>
              <small className="text-muted">Type: {item.type}</small>
              <p className="mb-1">{item.description}</p>
            </Link>
          ))}
        </div>
      )}
    </div>
  );
}
