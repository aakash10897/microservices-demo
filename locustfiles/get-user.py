from locust import HttpUser, task

class SocialMediaUser(HttpUser):

    @task
    def test_get_user(self):
        self.client.verify = False
        self.client.get('''/users/1''')

