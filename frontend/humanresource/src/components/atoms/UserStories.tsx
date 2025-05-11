import 'bootstrap/dist/css/bootstrap.min.css';
import './UserStories.css';
import { useEffect, useRef, useState } from 'react';

function UserStories() {
    const profileRef = useRef(null);
    const commentRef = useRef(null);
    const [profileVisible, setProfileVisible] = useState(false);
    const [commentVisible, setCommentVisible] = useState(false);

    useEffect(() => {
        const observer1 = new IntersectionObserver(
            ([entry]) => {
                if (entry.isIntersecting) {
                    setProfileVisible(true);
                }
            },
            { threshold: 0.3 }
        );

        const observer2 = new IntersectionObserver(
            ([entry]) => {
                if (entry.isIntersecting) {
                    setCommentVisible(true);
                }
            },
            { threshold: 0.3 }
        );

        if (profileRef.current) observer1.observe(profileRef.current);
        if (commentRef.current) observer2.observe(commentRef.current);

        return () => {
            observer1.disconnect();
            observer2.disconnect();
        };
    }, []);

    return (
        <div className="user-stories">
            <div className="row">
                <div className="col-7">
                    <div
                        ref={profileRef}
                        className={`user-profile ${profileVisible ? 'show' : ''}`}
                    >
                        <img className="human-image"   src="/img/humanik.png"/>
                        <h2>

                        </h2>
                    </div>
                </div>
                <div className="col-5">
                    <div
                        ref={commentRef}
                        className={`user-story-comment ${commentVisible ? 'show' : ''}`}
                    >
                        <h1>
                            User Stories
                        </h1>
                        <hr/>
                        <h5>
                            We’re simplifying HR together with thousands of human resources professionals. Discover how HR experts got started with Humin.
                        </h5>

                    </div>
                </div>
            </div>
        </div>
    );
}

export default UserStories;
