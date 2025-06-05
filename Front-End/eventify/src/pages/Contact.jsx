import { useState } from 'react';
import './Contact.css';
import contactImg from '../Images/Contact.png';

const Contact = () => {
    const [formData, setFormData] = useState({ name: '', surname: '', email: '', message: '' });
    const [status, setStatus] = useState('');
    const [isSubmitting, setIsSubmitting] = useState(false);

    const handleChange = e => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async e => {
        e.preventDefault();
        setIsSubmitting(true);
        setStatus('');

        try {
            const res = await fetch('/api/contact', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(formData)
            });

            if (res.ok) {
                setStatus('Message sent successfully!');
                setFormData({ name: '', surname: '', email: '', message: '' });
            } else {
                setStatus('There was an error sending your message.');
            }
        } catch (error) {
            setStatus('Failed to connect to the server.');
        }

        setIsSubmitting(false);
    };

    return (
        <>
            <div className="header-img">
                <img
                    src={contactImg}
                    alt="Contact"
                    style={{ width: '100%', height: '100%', objectFit: 'cover' }}
                />
            </div>

            <div className='container py-5'>
                <div className='contact-form fade-in'>
                    <h2 className='fw-bold mb-4 text-center'>Contact Us</h2>
                    <form onSubmit={handleSubmit}>
                        <div className='mb-3'>
                            <label className='form-label'>Name:</label>
                            <input
                                type='text'
                                className='form-control'
                                name='name'
                                value={formData.name}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <div className='mb-3'>
                            <label className='form-label'>Surname:</label>
                            <input
                                type='text'
                                className='form-control'
                                name='surname'
                                value={formData.surname}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <div className='mb-3'>
                            <label className='form-label'>Email:</label>
                            <input
                                type='email'
                                className='form-control'
                                name='email'
                                value={formData.email}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <div className='mb-3'>
                            <label className='form-label'>Message:</label>
                            <textarea
                                className='form-control'
                                name='message'
                                rows='5'
                                value={formData.message}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <button type='submit' className='btn btn-primary' disabled={isSubmitting}>
                            {isSubmitting ? 'Sending...' : 'Send'}
                        </button>
                    </form>

                    {status && (
                        <div className={`alert ${status.includes('success') ? 'alert-success' : 'alert-danger'} mt-3`}>
                            {status}
                        </div>
                    )}
                </div>
            </div>
        </>
    );
};

export default Contact;
