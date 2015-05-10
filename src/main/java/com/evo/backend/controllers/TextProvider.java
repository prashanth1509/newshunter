package com.evo.backend.controllers;

import com.evo.backend.configs.Endpoints;
import com.evo.backend.entities.News;
import com.evo.backend.entities.Push;
import com.evo.backend.entities.Users;
import com.evo.backend.modules.PushUtils;
import com.evo.backend.modules.TagFetcher;
import com.evo.backend.repos.NewsRepository;
import com.evo.backend.repos.PushRepository;
import com.evo.backend.repos.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Created by prashanth.a on 10/05/15.
 */
@RestController
public class TextProvider {

    private List<String> tags = Arrays.asList( "#blackandblue", "#BookBucketChallenge", /*"#catstairs",*/ "#dubsmash", "#harlemshake", "#Ebola", "#Ferguson", "#Mission272", "#fifaWorldCup", "#Hololens" );

    private static int count = 0;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private PushRepository pushRepository;

    @Autowired
    private UsersRepository usersRepository;


    @RequestMapping("/app/signup")
    public Object signup(
        @RequestParam(value = "name", required = true) String name,
        @RequestParam(value = "deviceId", required = true) String deviceId,
        @RequestParam(value = "pass", required = false) String pass,
        @RequestParam(value = "topics", required = false) String topics
    ) {
        String[] interestedTopics;
        if(topics!=null && topics.length()>0){
            interestedTopics = topics.split(",");
        }

        Users existing = usersRepository.findByDeviceId(deviceId);
        if(existing!=null && !existing.getId().isEmpty()){
            return true;
        }

        Users user = new Users();
        user.setName(name);
        user.setDeviceId(deviceId);
        user.setPass("default");

        usersRepository.save(user);

        return true;
    }

    @RequestMapping("/app/generateNews")
    public Object createNews() {
        newsRepository.deleteAll();
        for(String tag: tags){
            News news = new News();
            news.setTag(tag);
            news.setContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.");
            if(tag.equals("#harlemshake")){
                news.setContent("The making of a YouTube video turned into crime chaos after a Hialeah police officer crashed the party, which landed a group of people a trip to jail – including a banana man. The “Harlem Shake” dance is an YouTube sensation that swept the world overnight. The 30 second music video begins with a ‘starter’ in a helmet or mask and jumps to a group of whacky dancers in crazy costumes.");
            }
            else if(tag.equals("#Hololens")){
                news.setContent("The HoloLens could be a prop from a sci fi film; a headset Cyclops from the X-Men movie might wear loaded with the technology from Minority Report.Microsoft defends its latest technology, their answer to Google Glass, stoutly. A limited number of media at the Build Developers' Conference were told one morning that we had approval to try the holographic headsets, which combine virtual 3D images with reality");
            }
            else if(tag.equals("#Mission272")){
                news.setContent("In 1967, the Times of London carried a series of articles titled “India’s Disintegrating Democracy” written by their Delhi correspondent Neville Maxwell. This is how he describes the mood of the country: “the administration is strained and universally believed to be corrupt, the government and the governing party have lost public confidence and belief in themselves as well...[there is] a deep sense of defeat, an alarmed awareness that the future is not only dark but profoundly uncertain.” The more things change, the more they remain the same. These words may well be used to describe the current mood of the country.");
            }
            else if(tag.equals("#Ebola")){
                news.setContent("Chat with friends Web Sabarish Raghu 2m Manikhantan S 38m Suseendran Kandaswamy Web Varun Kumar Mobile Bhagyalakshmi Ramaiah Web Antony Britto Web Amarnath Thangavel T Web Baran Mahamood M Web Dave Gautham Web Hariharan L Web Naveenkumar Muguda Mobile Rajesh Radhakrishnan Mobile Revanth Gengiah Mobile Rohit Thumu Mobile Santhosh Saravanan Mobile Soumya Janardhanan Mobile Srikrissh Balasubramanian Web Visakan Chinnadurai Mobile Yuvarajan Udhayakumar 85% MobileManikhantan S Chat conversation start Saturday 10:05 wer? play arena u coming here? anga poi? 10 hours ago export PYTHONPATH=$PYTHONPATH:/usr/local/lib/python2.7/site-packages http://stevenloria.com/how-to-build-a-text-classification-system-with-python-and-textblob/ 8 hours ago train.zip 3 hours ago https://github.com/prashanth1509/newshuntClassifier python -c \"import django; print(django.get_version())\" A few seconds ago The HoloLens could be a prop from a sci fi film; a headset Cyclops from the X-Men movie might wear loaded with the technology from Minority Report. Microsoft defends its latest technology, their answer to Google Glass, stoutly. A limited number of media at the Build Developers' Conference were told one morning that we had approval to try the holographic headsets, which combine virtual 3D images with reality In 1967, the Times of London carried a series of articles titled “India’s Disintegrating Democracy” written by their Delhi correspondent Neville Maxwell. This is how he describes the mood of the country: “the administration is strained and universally believed to be corrupt, the government and the governing party have lost public confidence and belief in themselves as well...[there is] a deep sense of defeat, an alarmed awareness that the future is not only dark but profoundly uncertain.” The more things change, the more they remain the same. These words may well be used to describe the current mood of the country. However, Maxwell got one crucial detail wrong. He concluded that, “while Indians would soon vote in the fourth — and surely last — general election, the great experiment of developing India within a democratic framework has failed.” The World Health Organization declared Liberia free of Ebola on Saturday, making it the first of the three hardest-hit West African countries to bring a formal end to the epidemic. The room, packed with reporters, workers from Doctors Without Borders and other aid agencies and dignitaries, including the American ambassador to Liberia, Deborah R. Malac, burst into applause, and some people cried, according to health officials who were present. “The outbreak of Ebola virus disease in Liberia is over,” the W.H.O. said in a statement read by Dr. Alex Gasasira, the group’s representative to Liberia, in a packed conference room at the emergency command center in Monrovia, the capital.");
            }
            else if(tag.equals("#Ferguson")){
                news.setContent("Ferguson, Mo., is once again a flash point in this nation’s struggle to come to grips with itself, as its citizens are embroiled in a profound conversation about bias, policing, the criminal justice system, civil rights and social justice. The Department of Justice has released its scathing report documenting widespread racial targeting of citizens with fine and tickets. The city manager, the police chief and a judge cited in the report have stepped down. Cases will now be adjudicated outside the corrupt system described in the report. According to an article last week in The Tim");
            }
            else if(tag.equals("#fifaWorldCup")){
                news.setContent("Germany beat Argentina 1-0 to become the first European team to win a World Cup held in South America on Sunday. Bayern Munich star Goetze struck in the 113th minute to finally break Argentina's resistance as Lionel Messi's dream of emulating Diego Maradona ended in defeat. Germany has now won four World Cups, putting the European powerhouses just one behind Brazil's record tally of five. \"It's unbelievable what we have achieved. Whether we have the best individual player doesn't matter at all, you just need to have the best team,\" said delighted Germany captain Philipp Lahm. \"We improved throughout the tournament and didn't get down when things didn't always go our way, we just stuck to our path and at the end we're standing here as world champions. It's an unbelievable feeling.");
            }
            else if(tag.equals("#dubsmash")){
                news.setContent("Admit it. There are times you wanted to sound as awesome as our Superstar in Baasha, but were instead at the receiving end of your friends’ choice words that left you on the verge of tears. But no more. With Dubsmash, the app that has been going viral on social media, you can actually belt out a Padayappa line or for that matter a Vadivelu dialogue and sound exactly like the actor’s themselves.");
            }
            else if(tag.equals("#BookBucketChallenge")){
                news.setContent("Ayon Ghosh likes Sarcasm Society's post.Prasath Rajasekaran likes Rahul Bala's video: Chennai Amrita - TROLLED.Jaya Vasanthe likes Lesley Scen's photo.Divak Aran likes Anjana vj.Srivathsa Pasumarthi likes Ra Ja's post.Sathish Sundaramoorthy likes Adi Nagarajan's photo.Raksha likes Sofia Mary's photo.Mohamed Ibrahim commented on his own post.Sanjai Maxi likes Multiplex Movies's photo.Barath Raj likes Trollywood ;-)'s photo.Yokesh Thirumal likes FC Barcelona Is In My DNA's post.Nakul Moudgilposted toSwapnil VaibhavHappy bday Swapnil Vaibhav, How are you doing?Atul Kumar likes Ashwin Achu's post.Mani Rokr likes Narender Kumar's post.Nan Da likes Ranjith Kumar's photo.Sheerapthi Nathan likes Shah Rukh Khan's photo.Gautam Nagpal and Sourabh Sudha are now friends.Manas Modi likes Prasad Ethireddy's post.Ayon Ghosh commented on his own post.Ivin Jose likes Lucy Garnett's photo.Amar Nagaram commented on his own post.Naveenkumar Muguda likes Shefali Vaidya's post.MA Vinoth Kumarposted toGomathi SundaramHappy BirthdayDave Gautham likes Thooki adichurve paathuka's photo.Karthik Vvs likes GabeandJesss's link.Mohamed Ibrahim commented on his own post.Sindhuja Varma likes OfficialHuskylovers.com's photo.Phaneesh Nagaraja commented on Rashid Noorani's post.Ashok B S Boynan likes Electrical Engineering World's photo.Srinath Sekar likes Pradeep Kumar Muralidharan's post.Prabha Pearl likes Prathiba Shanthakumar's photo.Sanjai Maxi likes Scott Adkins Official Page's photo.Vishnu Vish likes Padmanabhan Sundar's post.Rajath Ramesh likes Suma Dinesh's photo.Raksha likes Humans of New York.Leena Jacob likes Roopali Elizabeth Ipe's photo.Hannah Rozeeta likes Esther Sylvia's photo.Sarvan JP likes Varun Kumar's post.Pravin Mukesh likes Vinodkumar Muruganathan's post.Karthick Murugan likes Emelda Immanuel's photo.Phaneesh Nagaraja likes Rashid Noorani's post.Yeshwanth V Shenoy and Chockesh Sathya are now friends.Srivathsa Pasumarthi likes Vijay Ganesh's photo.Divya Pai likes Vishwanath Savakar's photo.Prasanna Venkat likes Sriraman Sukumaran Govind's photo.Chat with friendsWebManikhantan SWebSabarish Raghu34mSuseendran KandaswamyWebBipul Jain8hVigneshwar RavichandranMobileVijay ShankarWebAmarnath Thangavel TMobileGautam GunasekaranWebKr Ace Kumar RamarajuMobileNaresh NeshWebNaveenkumar MugudaMobilePrasad PaiWebPraveen KumarMobileRajesh RadhakrishnanMobileRevanth GengiahMobileSam SaganWebSanjai MaxiMobileSrikrissh BalasubramanianMobileSrinivasan Manickam85%OnlineManikhantan SChat conversation startSaturday 10:05wer?play arenau coming here?anga poi?10 hours agoexport PYTHONPATH=$PYTHONPATH:/usr/local/lib/python2.7/site-packageshttp://stevenloria.com/how-to-build-a-text-classification-system-with-python-and-textblob/8 hours agotrain.zip3 hours agohttps://github.com/prashanth1509/newshuntClassifierpython -c \"import django; print(django.get_version())\"A few seconds agoThe HoloLens could be a prop from a sci fi film; a headset Cyclops from the X-Men movie might wear loaded with the technology from Minority Report.Microsoft defends its latest technology, their answer to Google Glass, stoutly. A limited number of media at the Build Developers' Conference were told one morning that we had approval to try the holographic headsets, which combine virtual 3D images with realityIn 1967, the Times of London carried a series of articles titled “India’s Disintegrating Democracy” written by their Delhi correspondent Neville Maxwell. This is how he describes the mood of the country: “the administration is strained and universally believed to be corrupt, the government and the governing party have lost public confidence and belief in themselves as well...[there is] a deep sense of defeat, an alarmed awareness that the future is not only dark but profoundly uncertain.” The more things change, the more they remain the same. These words may well be used to describe the current mood of the country.However, Maxwell got one crucial detail wrong. He concluded that, “while Indians would soon vote in the fourth — and surely last — general election, the great experiment of developing India within a democratic framework has failed.”The World Health Organization declared Liberia free of Ebola on Saturday, making it the first of the three hardest-hit West African countries to bring a formal end to the epidemic. The room, packed with reporters, workers from Doctors Without Borders and other aid agencies and dignitaries, including the American ambassador to Liberia, Deborah R. Malac, burst into applause, and some people cried, according to health officials who were present.“The outbreak of Ebola virus disease in Liberia is over,” the W.H.O. said in a statement read by Dr. Alex Gasasira, the group’s representative to Liberia, in a packed conference room at the emergency command center in Monrovia, the capital.Just before Dr. Gasasira’s statement, Luke Bawo, an epidemiologist, showed a map depicting all of Liberia in green with the number 42 superimposed on it. This represented that two maximum incubation periods of the virus, a total of 42 days, had passed since the safe burial of the last person confirmed to have had Ebola in the country, fulfilling the official criteria for concluding that human-to-human transmission of the virus has ended.Ferguson, Mo., is once again a flash point in this nation’s struggle to come to grips with itself, as its citizens are embroiled in a profound conversation about bias, policing, the criminal justice system, civil rights and social justice.The Department of Justice has released its scathing report documenting widespread racial targeting of citizens with fine and tickets. The city manager, the police chief and a judge cited in the report have stepped down. Cases will now be adjudicated outside the corrupt system described in the report. According to an article last week in The Times:“The Missouri Supreme Court, citing the need for ‘extraordinary action’ to restore trust in Ferguson’s court system after the Department of Justice blasted it for routinely violating constitutional rights, assigned a state appeals court judge on Monday to oversee all municipal cases.” But unfortunately, two police officers have also been shot in Ferguson.Germany beat Argentina 1-0 in extra time to win FIFA World Cup on Sunday. RIO DE JANEIRO (AFP) - Mario Goetze scored a superb extra-time winner as Germany beat Argentina 1-0 to become the first European team to win a World Cup held in South America on Sunday. Bayern Munich star Goetze struck in the 113th minute to finally break Argentina's resistance as Lionel Messi's dream of emulating Diego Maradona ended in defeat. Germany has now won four World Cups, putting the European powerhouses just one behind Brazil's record tally of five. \"It's unbelievable what we have achieved. Whether we have the best individual player doesn't matter at all, you just need to have the best team,\" said delighted Germany captain Philipp Lahm. \"We improved throughout the tournament and didn't get down when things didn't always go our way, we just stuck to our path and at the end we're standing here as world champions. It's an unbelievable feeling.Admit it. There are times you wanted to sound as awesome as our Superstar in Baasha, but were instead at the receiving end of your friends’ choice words that left you on the verge of tears. But no more. With Dubsmash, the app that has been going viral on social media, you can actually belt out a Padayappa line or for that matter a Vadivelu dialogue and sound exactly like the actor’s themselves.The app for Android and Apple, that has been rated above four out of five in both platform stores, lets you take a selfie video while simultaneously mixing it with any sound — so you can lip sync the dialogue while Arnold Schwarzenegger as Terminator says ‘I’ll be back’ in the background.Social media has gone into a tizzy, with Dubsmash videos swamping Facebook feeds and Twitter — on the latter, it received a whopping 4.54 lakh mentions in the past 30 days alone, according to analytics engine Topsy. Kollywood celebrities too have jumped on the Dubsmash bandwagon — actors Simbu, Premgi Amaren, Vishal with Suri and Radhika Sarathkumar with her daughter and composer Anirudh are some of the celebrities who’ve put up their Dubsmash videos.After the famous Ice Bucket Challenge and India's very own Rice Bucket Challenge, here is another challenge making its rounds on social media: The Book Bucket Challenge. If you have seen your friends either nominating or being nominated for telling the world about 10 books that have been with them for a while, you know what the Book Bucket Challenge is.However, there is more to it than what meets the eye. Not many of us know that The Book Bucket Challenge is a drive started by One Library per Village, an NGO based in Kerala. The mission of this NGO is to create awareness and share the latest tools, services and resources that add value to digital libraries, enrich user experiences and enhance associated people's career development and learning activities. ");
            }
            else if(tag.equals("#blackandblue")){
                news.setContent("f you haven’t heard about this gone-viral debate, you might Google the title heading right above and you’ll see.Which is the interesting part of the debate: how humans see color.   Painters for centuries have explored the nature of color and how we see it.  And what painters and scientists have discovered is that people really don’t see color the way most people think they see it.    To see color, really see it, painters have to unlearn shibboleths about color.");
            }
            news.setHeading(tag.toUpperCase().replace("#",""));
            newsRepository.save(news);
        }

        return true;
    }

    @RequestMapping("/app/startListening")
    public Object start() {
        TagFetcher tagFetcher = new TagFetcher("tagFetcher");
        tagFetcher.start();
        return null;
    }

    @RequestMapping("/app/reset")
    public Object reset(){
        pushRepository.deleteAll();
        usersRepository.deleteAll();

/*
        for(String tag: tags){
            News news = new News();
            news.setTag(tag);
            news.setContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.");
            news.setHeading(tag.toUpperCase().replace("#",""));
            newsRepository.save(news);
        }
*/

        return true;
    }

    @RequestMapping("/twitter-api/trending")
    public Object twitterTrends(){
        Map<String, List<String>> trends = new HashMap();

        //Simulate late events
        List<String> tagz = new ArrayList<String>();

        if(count>tags.size()-1)
            count = 0;

        tagz.add(tags.get(count));
        count = count + 1;

        trends.put("tags", tagz);
        return trends;
    }

    @RequestMapping("/twitter-api/getTexts")
    public Object twitterTexts(
        @RequestParam(value = "tag", required = true) String tagId
    ) {
        Map<String, List<String>> result = new HashMap();

        RestTemplate restTemplate = new RestTemplate();
        String apiURL = Endpoints.ALL_HASHES;

        Map<String,List<String>> allHashes = restTemplate.getForObject(apiURL, Map.class);

        if(allHashes.containsKey(tagId)){
            result.put("texts", allHashes.get(tagId));
        }

        return result;
    }

    @RequestMapping("/app/push")
    public Object push(
        @RequestParam(value = "label", required = true) String tagId,
        @RequestParam(value = "topic", required = true) String topic
    ){
        News news = newsRepository.findByTag(tagId);
        if(news!=null && news.getId().length()>0){
            //get device ids and push this news to all users
            List<Users> users = usersRepository.findAll();
            for(Users user: users){
                PushUtils util = new PushUtils(user.getDeviceId());
                util.setNewsId(news.getId());
                util.setHashTag(tagId);
                util.setTopic(topic);
                util.pushMessage();
            }
        }
        else{
            Push lazyPush = new Push();
            lazyPush.setPushed(false);
            lazyPush.setTag(tagId);
            pushRepository.save(lazyPush);
        }
        return "success";
    }

    @RequestMapping("/app/news")
    public Object getNews(
        @RequestParam(value = "newsId", required = true) String newsId
    ) {
        News news = newsRepository.findOne(newsId);
        return news;
    }


}
