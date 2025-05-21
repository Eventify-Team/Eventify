import { Link } from "react-router-dom";

function NotFound404() {
  return (
    <div className="container text-center py-5">
      <h1 className="display-1 fw-bold text-primary">404</h1>
      <p className="fs-3">Oops! Page Not Found</p>
      <p className="lead mb-4">Sorry we couldn't find that page.</p>
      <Link to="/Home" className="btn btn-primary rounded-pill px-4">
        Return to HomePage.
      </Link>
    </div>
  );
}

export default NotFound404;
