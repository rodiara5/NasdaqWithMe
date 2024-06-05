const ticker2 = document.getElementById('ticker').textContent;
const industry = document.getElementById('industry').textContent;

Promise.all([
    fetch(`/api/v1/nasdaq/ratios?ticker=${ticker2}`).then(response => response.json()),
    fetch(`/api/v1/nasdaq/industry?industry=${industry}`).then(response => response.json())
])
.then(([ratioData, industryData]) => {
    // ratioData와 industryData의 형식에 맞게 변환
    const tickerDates = ratioData.map(ticker => ticker.dailydate);
    const tickerPers = ratioData.map(ticker => ticker.per);
    const tickerPbrs = ratioData.map(ticker => ticker.pbr);

    const industryDates = industryData.map(industry => industry.dailydate);
    const industryPers = industryData.map(industry => industry.avgPER);
    const industryPbrs = industryData.map(industry => industry.avgPBR);

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
            text: `${ticker2} vs ${industry}`,
            align: 'left'
        },
        subtitle: {
            text: 'Source: <a target="_blank" href="https://www.indexmundi.com/agriculture/?commodity=corn">indexmundi</a>',
            align: 'left'
        },
        xAxis: {
            categories: tickerDates,
            crosshair: true,
            accessibility: {
                description: 'date'
            }
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
                data: tickerPers
            },
            {
                name: industry,
                yAxis: 0,
                data: industryPers
            }
        ]
    });
})
.catch(error => console.error('Error fetching data:', error));
