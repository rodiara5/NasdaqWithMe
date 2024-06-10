const ticker2 = document.getElementById('ticker').textContent;
const industry = document.getElementById('industry').textContent;

Promise.all([
    fetch(`/api/v1/nasdaq/price?ticker=${ticker2}`).then(response => response.json())
    
])
.then(([ratioData]) => {
    // ratioData와 industryData의 형식에 맞게 변환
    const tickerDates = ratioData.map(ticker => ticker.dailydate);
    const tickerprice = ratioData.map(ticker => ticker.price);

    // const industryDates = industryData.map(industry => industry.dailydate);
    // const industryPers = industryData.map(industry => industry.avgPER);
    // const industryPbrs = industryData.map(industry => industry.avgPBR);

    // const processedRatioData = [
    //     ratioData.per,
    //     ratioData.psr,
    //     ratioData.pbr,
    //     ratioData.ev_ebitda
    // ];

    // const processedIndustryData = [
    //     industryData.avgPER,
    //     industryData.avgPSR,
    //     industryData.avgPBR,
    //     industryData.avgEV_EBITDA
    // ];

    // Highcharts 차트 생성
    Highcharts.chart('container', {
        chart: {
            type: 'spline'
        },
        title: {
            text: `${ticker2}`,
            align: 'left'
        },
        xAxis: {
            categories: tickerDates,
            crosshair: true
            
        },
        yAxis: [{ // Primary yAxis for Market Cap
            labels: {
                format: '{value}',
                style: {
                    color: Highcharts.getOptions().colors[1]
                }
            },
            title: {
                text: 'PER',
                style: {
                    color: Highcharts.getOptions().colors[1]
                }
            }
        }],
        // yAxis: {
        //     min: 0,
        //     title: {
        //         text: 'Value'
        //     }
        // },
        tooltip: {
            valueSuffix: ''
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [
            {
                name: ticker2,
                yAxis: 0,
                data: tickerprice
            }
           
        ]
    });
})
.catch(error => console.error('Error fetching data:', error));
