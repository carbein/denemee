import 'bootstrap/dist/css/bootstrap.min.css';
import './ManagerPage.css';
import LeaveChart from "../../components/atoms/LeaveChart.tsx";
import { useState } from "react";

function ManagerPage() {
    const today = new Date();
    const days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
    const dayIndex = today.getDay();
    const dayName = days[dayIndex];
    const dateString = today.toLocaleDateString("en-US");

    const [chartData, setChartData] = useState({ total: 0, used: 0, remaining: 0 });

    const handleChartInfo = (info) => {
        setChartData(info);
    };

    return (
        <div className="row">
            <div className="col-2 fixed-side-bar">
                <div className="fixed-bar-image">
                    <img className="logo-left-menu" src="/img/logo1.png" alt="logo" />

                </div>
                <hr/>
                <div className="fixed-bar-button-container">
                    <button className="fixed-bar-buttons">
                        <img className="small-image-fixed-bar" src="/img/adminpage.png" />
                        Company Page
                    </button>
                    <button className="fixed-bar-buttons">
                        <img className="small-image-fixed-bar" src="/img/employee.png" />
                        Employee
                    </button>
                    <button className="fixed-bar-buttons">
                        <img className="small-image-fixed-bar" src="/img/expens.png" />
                        Salary
                    </button>
                    <button className="fixed-bar-buttons">
                        <img className="small-image-fixed-bar" src="/img/shift.png" />
                        Shift
                    </button>
                    <button className="fixed-bar-buttons">
                        <img className="small-image-fixed-bar" src="/img/assets.png" />
                        Assignment
                    </button>
                </div>
                <hr/>
                <div className="bottom-bar">
                    <button className="fixed-bar-buttons">
                        <img className="small-image-fixed-bar" src="/img/profileicon.png" />
                        Profile
                    </button>
                    <button className="fixed-bar-buttons">
                        <img className="small-image-fixed-bar" src="/img/settingsicon.png" />
                        Settings
                    </button>
                    <button className="fixed-bar-buttons">
                        <img className="small-image-fixed-bar" src="/img/helpicon.png" />
                        Help
                    </button>
                </div>
            </div>
            <div className="col-10">
                <div className="manager-page-header">
                    <h2>Hello Manager!</h2>
                    <h3>Today's Date: {dateString}, {dayName}</h3>
                    <hr/>
                </div>
                <div className="row">
                    <div className="col-3 box-dashboard">
                        <div className="box1-dashboard">
                            <div className="profile-settings-header">
                                <div className="col-8 profile-settings-header-name">
                                    <h3>Manager Name</h3>
                                </div>
                                <div className="col-4 profile-settings-header-icon">
                                    <img className="small-image-fixed-bar2" src="/img/profileicon.png" />
                                </div>
                            </div>
                            <div className="profile-settings-body">
                                <div>
                                    <h4>Title</h4>
                                    <h6>Company</h6>
                                </div>
                                <hr/>
                                <div className="account-button-container">
                                    <button className="accountbutton">
                                        Account →
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div className="box1-dashboard p-3">
                            <p> Employee Number : 20 </p>
                        </div>
                    </div>
                    <div className="col-3 box-dashboard">
                        <div className="box1-dashboard">
                            <div className="leave-settings-body">
                                <div>
                                    <LeaveChart onDataReady={handleChartInfo} />
                                    <p> Total : {chartData.total} </p>
                                    <p> Used :  {chartData.used} </p>
                                    <p> Remaining : {chartData.remaining}</p>
                                </div>
                                <hr/>
                                <div className="request-button-container">
                                    <button className="accountbutton">
                                        Request →
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="col-3 box-dashboard">
                        <div className="box1-dashboard row p-1">
                            <h3>Today's Shift List</h3>
                            <hr/>
                            <div className="col-7 fontstyle-shiftnames">
                                FirstName LastName1
                                FirstName LastName2
                                FirstName LastName3
                                FirstName LastName4
                                FirstName LastName5
                                FirstName LastName6
                            </div>
                            <div className="col-5 fontstyle-shifthours">
                                08:00-12:00
                                08:00-12:00
                                13:00-17:00
                                13:00-17:00
                                18:00-22:00
                                18:00-22:00
                            </div>
                        </div>
                    </div>
                    <div className="col-3 box-dashboard">

                    </div>
                </div>
            </div>
        </div>
    );
}

export default ManagerPage;
