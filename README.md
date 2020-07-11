# Project 4 - *InstagramClone*

**InstagramClone** is a photo sharing app using Parse as its backend.

Time spent: **23** hours spent in total

## User Stories

The following **required** functionality is completed:

- [x] User sees app icon in home screen.
- [x] User can sign up to create a new account using Parse authentication
- [x] User can log in and log out of his or her account
- [x] The current signed in user is persisted across app restarts
- [x] User can take a photo, add a caption, and post it to "Instagram"
- [x] User can view the last 20 posts submitted to "Instagram"
- [x] User can pull to refresh the last 20 posts submitted to "Instagram"
- [x] User can tap a post to view post details, including timestamp and caption.

The following **stretch** features are implemented:

- [x] Style the login page to look like the real Instagram login page.
- [ ] Style the feed to look like the real Instagram feed.
- [x] User should switch between different tabs - viewing all posts (feed view), capture (camera and photo gallery view) and profile tabs (posts made) using fragments and a Bottom Navigation View.
- [ ] User can load more posts once he or she reaches the bottom of the feed using endless scrolling.
- [x] Show the username and creation time for each post
- [ ] After the user submits a new post, show an indeterminate progress bar while the post is being uploaded to Parse
- User Profiles:
  - [ ] Allow the logged in user to add a profile photo
  - [ ] Display the profile photo with each post
  - [ ] Tapping on a post's username or profile photo goes to that user's profile page
  - [ ] User Profile shows posts in a grid view
- [ ] User can comment on a post and see all comments for each post in the post details screen.
- [ ] User can like a post and see number of likes for each post in the post details screen.

The following **additional** features are implemented:

- [ ] List anything else that you can get done to improve the app functionality!

Please list two areas of the assignment you'd like to **discuss further with your peers** during the next class (examples include better ways to implement something, how to extend your app in certain ways, etc):

1. After adding the tab navigation to my app, all of the fragments caused the onclicklisteners to stop working. I want to know, why did that happen?
2. I used the new view binding library a lot on this app, and sometimes it broke as a result. Why is it that the view binding libary has so many nuances?

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='walkthrough.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Credits

List an 3rd party libraries, icons, graphics, or other assets you used in your app.

- [Android Async Http Client](http://loopj.com/android-async-http/) - networking library


## Notes

Describe any challenges encountered while building the app.

I had a tough time with this app, alot of it was debugging random things.
1. I spent an hour trying to prevent duplicate users from signing up. I did not know parse did this for me
2. I spent 2 hours figuring out why Glide wouldn't load images correctly. I found out I needed to change something in the manifest file thanks to a TA
3. I had to constantly use the hints for the "command not found" error in my terminal. I still don't know why it was happening
4. After I added the tab navigation to my app and all of the fragments, it broke. My feed wasn't loading anything. I spent an hour with a TA fixing that. Turns out I was onCreating two times rather than once.
5. After Adding the tab navigation, all of my onclick listeners stopped working, so I had to search the internet why this was the case (this is after 4 pm). I found my answer 2 hours later. I needed to change the location of my onClicklistener.


## License

    Copyright [yyyy] [name of copyright owner]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
