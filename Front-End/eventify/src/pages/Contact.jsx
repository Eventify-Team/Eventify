import { useState } from 'react';
import './Contact.css';
import contactImg from '../Images/Contact.png';


const Contact = () => {
    const [formData, setFormData] = useState({ name: '',surname:'', email: '', message: '' });
    const [status, setStatus] = useState('');

    const handleChange = e => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async e => {
        e.preventDefault();
        try {
            const res = await fetch('/api/contact', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(formData)
            });

            if (res.ok) {
                setStatus('Το μήνυμα στάλθηκε επιτυχώς!');
                setFormData({ name: '',surname: '', email: '', message: '' });
            } else {
                setStatus('Υπήρξε σφάλμα κατά την αποστολή.');
            }
        } catch (error) {
            setStatus('Σφάλμα σύνδεσης με τον διακομιστή.');
        }
    };

    return (
        <>
            <div className="header-img">
                <img src={contactImg}
                alt="Contact"
                style={{ width: '100%', height: 'auto', objectFit: 'cover' }} />
            </div>

            <div className='container py-5'>
                <div className='contact-form fade-in'>
                    <h2 className='fw-bold mb-4 text-center'>Contact Us</h2>
                    <form onSubmit={handleSubmit}>
                        <div className='mb-3'>
                            <label className='form-label'>Name:</label>
                            <input type='text' className='form-control' name='name' value={formData.name} onChange={handleChange} required />
                        </div>
                        <div className='mb-3'>
                            <label className='form-label'>Surname:</label>
                            <input type='text' className='form-control' name='name' value={formData.name} onChange={handleChange} required />
                        </div>
                        <div className='mb-3'>
                            <label className='form-label'>Email:</label>
                            <input type='email' className='form-control' name='email' value={formData.email} onChange={handleChange} required />
                        </div>
                        <div className='mb-3'>
                            <label className='form-label'>Message:</label>
                            <textarea className='form-control' name='message' rows='5' value={formData.message} onChange={handleChange} required />
                        </div>
                        <button type='submit' className='btn btn-primary'>Send</button>
                    </form>
                    {status && (
                        <div className={`alert ${status.includes('επιτυχώς') ? 'alert-success' : 'alert-danger'} mt-3`}>
                            {status}
                        </div>
                    )}
                </div>
            </div>
        </>
    );
};

export default Contact;
