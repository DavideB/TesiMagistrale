class Config(object):
    """
    Configuration class for xSpark-dagsymb
    """    
    class Heuristic(Enum):
        CONTROL = 0
        FIXED = 1
        CONTROL_UNLIMITED = 2
        SYMEX_CONTROL_UNLIMITED = 3
    
    REGION = ""             #"""Region of AWS to use"""
    PROVIDER = ""           #"""Provider to be used"""
    AZ_KEY_NAME = ""        #""" name of Azure private key """
    AZ_PUB_KEY_PATH = ""    #""" path of Azure public key """
    AZ_PRV_KEY_PATH = ""    #""" path of Azure private key """
    AWS_ACCESS_ID = ""      #""" Azure access id """
    AWS_SECRET_KEY = ""     #""" Azure secret key """
    AZ_APPLICATION_ID = ""  #""" Azure application id """
    AZ_SECRET = ""          #""" Azure secret """
    AZ_SUBSCRIPTION_ID = "" #""" Azure subscription id """
    AZ_TENANT_ID = ""       #""" Azure tenant id """
    ...
    HEURISTIC = Heuristic.SYMEX_CONTROL_UNLIMITED
    ...
    def __init__(self):
        self.config_credentials("credentials.json")
        self.config_setup("setup.json")
        self.config_control("control.json")
        self.update_config_parms(self)
    ...
    def config_experiment(self, filepath, cfg):
    ...
    def config_setup(self, filepath):
    ...    
config_instance = Config()